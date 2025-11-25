import http from 'k6/http';
import { check } from 'k6';

export const options = {
  scenarios: {
    sampleTest: {
      executor: 'constant-arrival-rate',
      exec: 'sampleTest',
      duration: '5s',
      rate: 1,
      timeUnit: '1s',
      preAllocatedVUs: 1,
      maxVUs: 1,
    }
  }
};

export function sampleTest() {
  const res = http.get('http://host.docker.internal:8003/consulta/pessoas/all');
  check(res, { 'status é 200': (r) => r.status === 200 });
}
