# API Java - Cadastro de Pessoas 📋 👱‍♀️

![Prévia](https://github.com/jefersongjr/api-java-tt/blob/main/api-java-tt/src/images/1%200-Hyu03i97pVH5TCwlscAg.png)
<h4 align="center"> 
	🚧  API Java 🚀 Em construção...  🚧
</h4>

<p align="center">
 <a href="#sobre">Sobre</a> • 
 <a href="#ferramentas">Tecnologias</a> • 
 <a href="#requisitos">Como executar: </a> • 
 <a href="#instrucoes">Como Usar:</a> • 
 <a href="#autor">Autor</a>
</p>

<h2 id="sobre"> 🚀📋 Sobre: </h2>

Essa foi desenvolvida por mim ,[Jeferson Gomes](https://www.linkedin.com/in/jefersongjr/)
na etapa técnica do processo seletivo da [Attornatus Procuradoria Digital](https://www.linkedin.com/company/attornatus-procuradoria-digital/) . <br>
Consistia em contruir uma API simples, para o cadastro de pessoas e também, escrever testes para<br>
suas funcionalidades.

<h2 id="ferramentas"> 🛠️ Construído com: </h2>

* Java
* Spring Boot
* JPA
* JUnit 5
* Mockito

<h2 id="requisitos"> 📖 Como Executar: </h2>

<h4> ✔️ Rodando o Back End (servidor)</h4>
Antes de começar, você vai precisar ter instalado em sua máquina as seguintes ferramentas:
[Git](https://git-scm.com), [Java-JDK](https://www.oracle.com/java/technologies/downloads/). 
Além disto é bom ter um editor para trabalhar com o código como o [Eclipse](https://www.eclipse.org/downloads/)

<h4> 🎲 Rodando o Back End (servidor)</h4>

```

# Clone este repositório
$ git clone git@github.com:jefersongjr/api-java-tt.git

# Acesse a pasta do projeto
$ navegue ate a pasta root do projeto

#os dois próximos passos devem ser executados no diretório que tem o arquivo pom.xml

# Instale as dependências
$ mvn install

# Execute a aplicação
$ mvn spring-boot:run

# O servidor iniciará na porta:8080
# O Banco de dados h2 iniciará na porta:8080/h2-console

```
<h2 id="instrucoes"> 💻 Usando a Aplicação: </h2>

<h3> 📊 Banco de Dados </h3>

- O banco de dados pode ser acessado na porta:8080/h2-console 
- Os dados de acesso estão no arquivo ../src/main/resource/application.yml
- O Banco de dados tem duas entidades: Pessoa e Endereço. Ambas iniciam vazias.

![db](https://github.com/jefersongjr/api-java-tt/blob/main/api-java-tt/src/images/db.jpg)

<h3> Cadastrando uma Pessoa: </h3>

<h5> Método Post: "/pessoas" <h5>			
- Como primeiro passo deve-se criar uma pessoa.
- A API não permitirá criar um endereço sem nenhuma pessoa criada.
- Ao criar uma pessoa o endereço receberá o valor **null**

 Ao enviar uma requisição bem sucedida nesse formato,				
```
{
  "nome": "Daniel",
  "dataDeNascimento": "20/01/1991",
  "endereco": 1
}

```
a Aplicação devolverá uma resposta assim: 
				
![responsePost](https://github.com/jefersongjr/api-java-tt/blob/main/api-java-tt/src/images/repostaPost.jpg)


