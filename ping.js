// ping.js
import http from "k6/http";

export default function () {
  const res = http.get("http://host.docker.internal:8003/consulta/veiculos?placa=ABC1234");
  console.log("Status da API:", res.status);
  console.log("Tempo de resposta:", res.timings.duration, "ms");
}
