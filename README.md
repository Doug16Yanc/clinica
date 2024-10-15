<h1 align="center" width="100%">
  Sistema de consultório médico com JavaFX e MySQL
</h1>

<p align="center">
  
https://github.com/user-attachments/assets/825593a1-f01a-4a6c-bd00-38d853601b31

</p>


Este projeto foi desenvolvido como parte da disciplina de **Programação Orientada a Objetos**, com o objetivo de criar um sistema para a gestão de um consultório médico. O sistema utiliza as seguintes tecnologias:

## 🛠️ Tecnologias Utilizadas

<img src="https://img.shields.io/static/v1?message=JavaFX&logo=java&label=&color=007396&logoColor=white&style=for-the-badge" height="40" /> 
<img src="https://img.shields.io/static/v1?message=MySQL&logo=mysql&label=&color=4479A1&logoColor=white&style=for-the-badge" height="40" />

Inspirado em um projeto anterior desenvolvido com **Swing**, aprimoramos a experiência do usuário, criando uma interface mais limpa e moderna. Embora tenhamos enfrentado alguns desafios com a marcação e estilização do FXML, a experiência de uso do **SceneBuilder** nos ajudou a superar essas dificuldades, resultando em uma aplicação visualmente agradável e funcional.

## 🩺 Funcionalidades

1. **Autenticação de Médicos e Pacientes**: 
   - Sistema de login que diferencia médicos de pacientes, proporcionando níveis de acesso adequados a cada perfil.
   
2. **Cadastro e Gestão de Pacientes**: 
   - Permite o registro de novos pacientes, edição de informações e listagem de todos os pacientes cadastrados.
   
3. **Agendamento de Consultas**: 
   - Pacientes podem agendar consultas com médicos de diferentes especialidades, descrevendo o motivo da consulta.
   
4. **Realização de Consultas**: 
   - Médicos têm acesso à lista de consultas agendadas e podem registrar observações e resultados ao final da consulta.

## 🚀 Execução do Projeto

### Pré-requisitos

- <img src="https://img.shields.io/static/v1?message=Java&logo=oracle&label=&color=F80000&logoColor=white&style=for-the-badge" height="25" />  Versão 8 ou superior.
- <img src="https://img.shields.io/static/v1?message=MySQL&logo=mysql&label=&color=4479A1&logoColor=white&style=for-the-badge" height="25" /> Instância do banco de dados configurada.

### Configuração

1. Clone o repositório:
   ```bash
   git clone https://github.com/Doug16Yanc/clinica.git
   ```

2. Importe o projeto no seu ambiente de desenvolvimento.

3. Configure a conexão com o MySQL no arquivo de propriedades:
   ```properties
   db.url=jdbc:mysql://localhost:3306/nome-do-banco
   db.username=seu-usuario
   db.password=sua-senha
   ```

4. Execute o projeto pelo seu IDE ou pelo terminal:

## 💡 Considerações Finais

Este projeto foi um excelente exercício para a aplicação dos conceitos de **Programação Orientada a Objetos** (POO) e o uso de **bancos de dados relacionais**. As dificuldades encontradas no desenvolvimento, como a complexidade da estilização com FXML, foram superadas com dedicação e aprendizado contínuo.
