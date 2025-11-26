import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  scenarios: {
    consultaVeiculos: {
      executor: 'ramping-vus',
      exec: 'sampleTest',
      startVUs: 0,
      stages: [
        { duration: '15s', target: 20 }, // sobe de 0 -> 20 usuários
        { duration: '15s', target: 50 }, // sobe de 20 -> 50 usuários
        { duration: '30s', target: 50 }, // mantém 50 usuários por 30s
        { duration: '10s', target: 0 },  // rampa pra baixo
      ],
      gracefulRampDown: '5s',
    },
  },
  thresholds: {
    http_req_failed: ['rate<0.01'],      // menos de 1% de falhas
    http_req_duration: ['p(95)<500'],   // 95% das reqs abaixo de 500ms (ajuste se precisar)
  },
};

export function sampleTest() {
  const res = http.get(
    'http://host.docker.internal:8003/consulta/veiculos?placa=abc-1234'
  );

  check(res, {
    'status é 200': (r) => r.status === 200,
  });

  // pequeno "think time" pra simular usuário real
  sleep(1);
}
