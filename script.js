import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 100,              // 50 usuários simultâneos
  duration: '60s',      // teste de 1 minuto
  thresholds: {
    http_req_failed: ['rate<0.01'],      // menos de 1% de falhas
    http_req_duration: ['p(95)<200'],   // 95% das requisições abaixo de 200ms (ajuste se precisar)
  },
};

export default function () {
//  const url = 'http://localhost:8003/consulta/veiculos/por-placa?placa=ABC1D23';
	const url = 'http://host.docker.internal:8003/consulta/veiculos/por-placa?placa=ABC1D23';


  const res = http.get(url);

  check(res, {
    'status 200': (r) => r.status === 200,
  });

  // pequeno think time pra não ficar martelando 100% "robô"
  sleep(0.5);
}

