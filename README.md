
# 🚀 Avaliação de Desempenho entre os Padrões REST e gRPC

Este projeto tem como objetivo comparar, de forma prática e quantitativa, o desempenho dos padrões de comunicação **REST** e **gRPC** dentro de um **ambiente de microsserviços**, utilizando **testes de carga**, análise de métricas e técnicas de avaliação de desempenho.

## 📌 1. Visão Geral do Projeto

- **Propósito:** Avaliar como **REST** e **gRPC** se comportam sob diferentes cargas, ambientes e padrões de comunicação.
- **Objetivo principal:** Identificar qual padrão apresenta menor latência, maior throughput e maior estabilidade sob carga.
- **Cenário:** O projeto simula um ambiente com três serviços:
  - `gateway-service`
  - `veiculo-service`
  - `multa-service`
  - `pessoa-service`
- Cada serviço possui **implementação REST e gRPC**, e um **gateway REST** recebe requisições do cliente via REST no seu endpoint e chama o serviço de veículos via gRPC ou REST.

### 🧭 Fluxo geral da requisição (REST ou gRPC)

1. O cliente (gateway-service) recebe a requisição:

GET /consulta/veiculos/por-placa?placa=ABC1D23

2. O gateway consulta o `veiculo-service`.
3. O `veiculo-service` consulta o `multa-service`.
4. O gateway extrai o CPF do proprietário e consulta o `pessoa-service`.
5. O gateway consolida os dados e devolve a resposta final.

> **Obs.:**  
> No REST as comunicações internas utilizam **Feign Client**.  
> No gRPC, os serviços se comunicam diretamente via protobuf.

## ⚙️ 2. Instalação e Configuração

### ✔ Pré-requisitos

| Ferramenta | Versão recomendada |
|------------|--------------------|
| Java | 17 |
| Spring Boot | 3.5.7 |
| Docker / Docker Compose | latest |
| PostgreSQL | 14+ |
| k6 (testes de carga) | latest |
| Git | Opcional |

### 📥 Clonar o repositório

```bash
git clone https://github.com/juaohenrique/rest-vs-grpc

````

### 🐘 Subir banco de dados via Docker

```bash
docker compose up -d postgres
```

### ▶ Rodar os serviços

#### Via Maven:

```bash
mvn clean install
```

### ▶ Se alterar qualquer .proto, será necessário atualizar as classes stubs

#### Via Maven:

```bash
mvn clean install
```

> **Obs.:**  
> No ambiente gRPC, cada service tem seus arquivos de contrato .proto.

> Se houver mudança em quaisquer contratos, os novos .proto também deverão ser atualizados em todos os serviços que os utilizem.

> Para contornar esse problema, a melhor solução é ter um projeto de contratos importado como dependência maven nos demais serviços.


#### Via Docker Compose:

```bash
docker compose up -d
```

> O arquivo `docker-compose.yml` organiza:
> `gateway-service`, `veiculo-service`, `pessoa-service`, `multa-service` e `postgres`.

## 🧾 3. Contratos Protobuf (gRPC)

Os arquivos `.proto` definem os modelos e serviços utilizados na comunicação gRPC.


### 🚗 **veiculo.proto**

```proto
syntax = "proto3";

package veiculo;

option java_package = "br.com.jh.stubs.veiculo";
option java_multiple_files = true;

import "multa.proto";

service ConsultaVeiculo {
    rpc findByPlaca (VeiculoRequest) returns (VeiculoMultaResponse);
    rpc findByProprietario (VeiculoRequest) returns (ListaVeiculoResponse);
    rpc FindAll (Empty) returns (ListaVeiculoResponse);
}

message Empty {}

message VeiculoRequest {
    string placa = 1;
    string cpfProprietario = 2;
}

message VeiculoMultaResponse {
    int32 id = 1;
    string placa = 2;
    string ano = 3;
    string marca = 4;
    string modelo = 5;
    string cor = 6;
    string cpfProprietario = 7;
    
    repeated multa.v1.Multa listaMultas = 10;
}

message VeiculoResponse {
    int32 id = 1;
    string placa = 2;
    string ano = 3;
    string marca = 4;
    string modelo = 5;
    string cor = 6;
    string cpfProprietario = 7;
}

message ListaVeiculoResponse {
    repeated VeiculoResponse listaVeiculos = 1;
}
```

### 🧾 **multa.proto**

```proto
syntax = "proto3";

package multa.v1;

service ConsultaMultas {
    rpc ListarPorPlaca (MultaRequest) returns (MultaResponse);
}

message MultaRequest {
    string placa = 1;
}

message Multa {
    int32 id = 1;
    string placa = 2;
    string ctb = 3;
}

message MultaResponse {
    repeated Multa listaMultas = 1;
}
```

### 👤 **pessoa.proto**

```proto
syntax = "proto3";

package pessoa;

option java_package = "br.com.jh.stubs";
option java_multiple_files = true;

service ConsultaPessoa {
    rpc FindByCpf (PessoaRequest) returns (PessoaResponse);
    rpc FindByNome (PessoaRequest) returns (ListaPessoaResponse);
    rpc FindAll (Empty) returns (ListaPessoaResponse);
}

message Empty {}

message PessoaRequest {
    string nome = 1;
    string cpf = 2;
}

message PessoaResponse {
    int32 id = 1;
    string nome = 2;
    string nascimento = 3;
    string fone = 4;
    string endereco = 5;
    string cpf = 6;
}

message ListaPessoaResponse {
    repeated PessoaResponse listaPessoas = 1;
}
```

## 🧪 4. Uso — Exemplos de Requisição

### 🔗 Endpoint REST para testes

```
GET http://localhost:8003/consulta/veiculos/por-placa?placa=ABC1D23
```

### 📤 Exemplo de resposta JSON

```json
{
  "id": 1,
  "placa": "ABC1D23",
  "ano": "2023",
  "marca": "Volkswagen",
  "modelo": "Gol",
  "cor": "Prata",
  "cpfProprietario": "123.456.789-00",
  "multas": [
    {
      "id": 1,
      "placa": "ABC1D23",
      "ctb": "Excesso de velocidade (art. 218, I) - até 20% acima"
    }
  ],
  "pessoa": {
    "id": 1,
    "nome": "João Silva Santos",
    "nascimento": "1985-03-15",
    "fone": "(11) 98765-4321",
    "endereco": "Rua das Flores, 123",
    "cpf": "123.456.789-00"
  }
}
```

## 🏗 5. Arquitetura / Estrutura

### 🧩 Microsserviços

| Serviço             | Protocolo   | Função                       |
| ------------------- | ----------- | ---------------------------- |
| **gateway-service** | REST        | Orquestra chamadas REST/gRPC |
| **veiculo-service** | REST + gRPC | Consulta veículo e multas    |
| **multa-service**   | REST + gRPC | Lista multas por placa       |
| **pessoa-service**  | REST + gRPC | Consulta pessoa              |

---

### 📂 Estrutura sugerida dos diretórios

```
/gateway-service
/veiculo-service
/multa-service
/pessoa-service
/common-protos
/k6-scripts
/docs
docker-compose.yml
README.md
```

## 📊 6. Testes de Desempenho (k6)

### Script utilizado

```js
import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
  vus: 100,
  duration: '60s',
  thresholds: {
    http_req_failed: ['rate<0.01'],
    http_req_duration: ['p(95)<200']
  },
};

export default function () {
  const url = 'http://localhost:8003/consulta/veiculos/por-placa?placa=ABC1D23';
  const res = http.get(url);

  check(res, { 'status 200': (r) => r.status === 200 });

  sleep(0.5);
}
```

## 🔢 7. Tabela de sinais
### Fatores e sinais
| Fator                        | -1    | 1      |
|-----------------------------|-------|--------|
| **A – Nº de usuários**      | 50    | 100    |
| **B – Padrão de comunicação** | gRPC  | REST   |
| **C – Ambiente**            | Local | Remoto |

### Tabela fatorial
| A   | B   | C   | AB  | AC  | BC  | ABC | p95 (ms) | req/s  |
|-----|-----|-----|-----|-----|-----|-----|----------|--------|
| -1  | -1  | -1  | +1  | +1  | +1  | -1  | 114.99   | 89.87  |
| -1  | -1  | +1  | +1  | -1  | -1  | +1  | 204.20   | 85.59  |
| -1  | +1  | -1  | -1  | +1  | -1  | +1  | 106.49   | 91.44  |
| -1  | +1  | +1  | -1  | -1  | +1  | -1  | 571.27   | 67.15  |
| +1  | -1  | -1  | -1  | -1  | +1  | +1  | 143.17   | 177.44 |
| +1  | -1  | +1  | -1  | +1  | -1  | -1  | 664.31   | 141.53 |
| +1  | +1  | -1  | +1  | -1  | -1  | -1  | 930.74   | 104.83 |
| +1  | +1  | +1  | +1  | +1  | +1  | +1  | 1040.00  | 114.17 |
| **Efeito** | **445.32** | **380.46** | **296.10** | 201.17 | 19.10 | -9.08 | -196.86 | **471.90 ms** | — |


## 🎯 8. Resultados obtidos
### 📌 Fator A
  - Aumentar quantidade de usuários aumenta o tempo de resposta;
  - Quando usuários sobem de 50 → 100, o p95 aumenta em 445 msem média.
  
### 📌 Fator B
   - REST aumenta o p95 em 380,46 ms;
   - REST piora muito mais que gRPC ao subir a carga.

### 📌 Fator C
	- Executar remotamente aumenta o p95 em 296.10 ms;

### 📌 Fator AB
	- Com 50 usuários, REST e gRPC são mais próximos;
	- REST sofre mais com o aumento do número de usuários.

### 📌 Fator AC
	- Crescimento de usuários afeta mais o ambiente remoto, mas o efeito é pequeno comparado ao impacto de A ou C.
	- 
### 📌 Fator BC
	- REST e Remoto são não pioram tanto quanto B e C isolados.
	- 
### 📌 Fator ABC
	- O pior desempenho ocorre quando os fatores estão no nível +1;
	- Pior caso é 100 usuários, REST e Remoto.

### 📊 Gráfico comparativo
![ gRPC x REST - p95, trhoughput](docs/img/grafico-resultado.png)

## 📝 11. Conclusão do experimento
   - A análise fatorial mostrou que aumentar quantidade de usuários tem maior impacto no p95;
   - Pra análise realizada, a quantidade de usuários simultâneos são o maior gargalo;
   - O padrão REST apresentou desempenho muito pior que o gRPC, mesmo em um ambiente de testes estável;
   - O efeito B é muito forte e quase tão grande quanto o efeito A;
   - REST tem escalabilidade pior que gRPC;
   - O gRPC é adequado para cenários que exigem alta escalabilidade e baixa latência;
  
## 👤 10. Autor

**João Henrique**
Desenvolvedor Backend | Java + Spring Boot | Arquitetura de Software

