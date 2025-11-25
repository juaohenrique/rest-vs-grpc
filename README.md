# REST-vs-gRPC
Avaliação de desempenho de diferentes padrões de comunicação em uma arquitetura de microservices.

1. RPC - Remote Procedure Call
   - São chamadas de função pelo cliente que são executadas remotamente direto no servidor;
   - Ex.: gRPC, Java RMI, XML-RPC, JSON-RPC, etc.



2. gRPC - Google Remote Procedure Call
   - O gRPC é um framework desenvolvido pelo Google que possibilita a comunicação entre sistemas de forma leve, rápida e independente da linguagem de programação;
    
   - Características:
   - Independente de linguagem;
   - Compactação eficiente de dados;
   - Serialização e desserialização eficientes;
   - Simples de usar;
   - Usa HTTP/2 com Protocol Buffers
   - Protocol Buffers (protobuf)

3. Protocol Buffers (protobuf)
   - o gRPC usa o HTTP/2 para se comunicar e trafegar dados e por isso usa o Protocol Buffers;
   - seu objetivo é ser leve, rápido e simples;
   - antes de enviar os dados são convertidos em binários, tornando a mensagem mais leve;
   - usa uma Linguagem de Definição de Interfaces (IDL);
   - a chamada de um método remoto é, essencialmente, uma chamada comum de um método local, que é interceptada por um modelo local do objeto remoto e transformada em uma chamada de rede, ou seja, você está chamando um método local como se fosse um método remoto.

4.  Arquivo .proto
   - é um arquivo de texto que define o contrato (interface) entre o cliente e o servidor no gRPC.
   - é escrito na linguagem Protocol Buffers (protobuf);
   - define a estrutura dos dados que queremos serializar;
   - os dados do protobuf são estruturados como mensagem;
   - Esse arquivo é a única fonte da verdade sobre:
     - Quais métodos (RPCs) o serviço oferece;
     - Quais dados entram (parâmetros/request);
     - Quais dados saem (resposta/response);
     - Como esses dados são estruturados (campos, tipos, números de campo, etc);
   
    Exemplo:
        
        // define a versão do protobuf que estamos usando;
        syntax = "proto3"; 

        // usado para resolução de conflitos;
        package greeting;   

        // pacote onde serão geradas as classes a partir do .proto;
        option java_package = "br.com.joao"; 

        
        // nome da função que pode ser chamada "greet";
        // a função "greet" recebe uma entrada do tipo ClientInput;
        // a função "greer" retorna uma saída do tipo ServerOutput;

        service Greeter {   // representa o nome do serviço "Greeter"
          rpc greet (ClientInput) returns (ServerOutput) {}
        }                                                   

        
        // definimos que a entrada em 2 atributos: "greeting" e "name";
        message ClientInput {
          string greeting = 1;
          string name = 2;
        }

        // saida do servidor definida com o atributo "message"
        message ServerOutput {
          string message = 1;
        }

![Arquitetura gRPC](arq-grpc.png)
![Arquitetura REST](arq-rest.png)
                
5.  Define o protobuf de multa-service
6.  Define o protobuf de veiculo-service
7.  Define o protobuf de pessoa-service
8.  Define o protobuf de gateway-service