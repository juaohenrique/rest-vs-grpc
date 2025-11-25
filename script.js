import http from 'k6/http';
import { check } from 'k6';

export const options = {
  scenarios: {
    consultaVeiculos: {
      executor: 'ramping-arrival-rate',
      exec: 'sampleTest',
      // Duração total: ~7 minutos
      startRate: 1,          // começa com 1 requisição/s
      timeUnit: '1s',        // unidade da taxa
      preAllocatedVUs: 10,   // VUs mínimos já alocados
      maxVUs: 100,           // limite máximo de VUs que o k6 pode usar
      stages: [
        { duration: '2m', target: 5 },   // 2 min a caminho de 5 req/s (carga baixa)
        { duration: '3m', target: 20 },  // 3 min subindo até 20 req/s (carga média)
        { duration: '2m', target: 50 },  // 2 min subindo até 50 req/s (carga mais pesada)
      ],
    },
  },
  thresholds: {
    // menos de 1% de falhas
    http_req_failed: ['rate<0.01'],
    // 95% das requisições abaixo de 500ms (ajuste conforme sua realidade)
    http_req_duration: ['p(95)<500'],
  },
};

export function sampleTest() {
  const res = http.get(
    'http://host.docker.internal:8003/consulta/veiculos?placa=abc-1234'
  );

  check(res, {
    'status é 200': (r) => r.status === 200,
  });
}
