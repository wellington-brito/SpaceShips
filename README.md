## SpaceShips

### **que você fez**
 _Implementei uma aplicação para acessar a api SWAPI apenas na primeira execução.
 _Na sequência os dados nesse caso detalhes das Naves, são armazenados localmente permitindo visualização off-line._
 _Indicação visual de que os dados de uma das Naves foi visualizada pelo usuário sempre que ele retornar à lista._
 Botão para visualizar apenas as naves que o usuário clicou e o somatório do Cost in Credits está sendo exbido através de um Toast._


### **Como você fez**
 _Decidi que apenas primeira execução deveria utilizar classe MyAsyncTask para buscar os dados direto da api._
 _Posteriormente a mesma classe é acessada para manipular os dados em paralelo à tela inicial por meio do banco de dados local._

 _Iniciei o desenvolvimento da aplicação com a ideia de utilizar a Room Persistence Library do Google, porém tive meu primeiro_
 _contato com essa lib enquanto pesquisava para o desafio então preferi otimizar o tempo utilizando a lib ORMLite._

 _O mesmo se aplica ao uso do Retrofit em relação à manipulação dos dados da api._

### **Por que você fez**
_Desenvolvi a aplicação dessa forma para que pudesse entregar um projeto funcional, e, considerando cenários e situações semelhantes,
realizar melhorias._