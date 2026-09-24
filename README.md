# 🗄️ Java Desktop Application & Database Management (DAO Pattern)

[![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![SQLite](https://img.shields.io/badge/SQLite-003B57?style=for-the-badge&logo=sqlite&logoColor=white)](https://www.sqlite.org/)
[![SLF4J](https://img.shields.io/badge/Logging-SLF4J-green?style=for-the-badge)](https://www.slf4j.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)

## 📌 Visão Geral
Aplicação Desktop desenvolvida em **Java** focada na implementação de boas práticas de persistência de dados, arquitetura limpa e manipulação de base de dados relacional (**SQLite**) via **JDBC**.

O projeto exemplifica a separação correta de responsabilidades através do padrão de projeto **DAO (Data Access Object)**, abstraindo a lógica de acesso aos dados e oferecendo uma interface gráfica (**GUI**) para interação com o utilizador.

---

### 🔑 Principais Funcionalidades & Destaques Técnicos

- **Padrão DAO (Data Access Object):** Isolamento total entre as regras de negócio e os comandos SQL.
- **Conexão Dinâmica via JDBC:** Gestão eficiente da ligação à base de dados SQLite (`meu_banco_de_dados.db`).
- **Automação de Schema:** Criação e verificação automática da estrutura das tabelas na inicialização (`CriadorTabela`).
- **Interface Gráfica (GUI):** Formulários interativos para operações do sistema (`ProdutoGUI`).
- **Gestão de Logs:** Integração com **SLF4J** para rastreabilidade e diagnósticos de execução.

---

## 🏗️ Arquitetura do Projeto

```text
├── ConexaoDB.java       # Gestão do ciclo de vida da conexão JDBC
├── CriadorTabela.java   # Script de automação DDL para criação do schema
├── Produto.java         # Entidade / Modelo de Dados (POJO)
├── ProdutoDAO.java      # Camada de acesso a dados (Operações CRUD)
├── ProdutoGUI.java      # Camada de Apresentação (Interface Gráfica)
└── Main.java            # Ponto de entrada do sistema

🛠️ Tecnologias Utilizadas
Linguagem: Java (JDK 8+)

Base de Dados: SQLite

Driver JDBC: sqlite-jdbc-3.46.0.0.jar

Logging Framework: slf4j-api & slf4j-simple

🚀 Como Executar o Projeto Localmente
Pré-requisitos
Java Development Kit (JDK) instalado.

Git instalado no seu sistema.
git clone [https://github.com/thiagopfteles-afk/Projeto-DB.git](https://github.com/thiagopfteles-afk/Projeto-DB.git)
cd Projeto-DB
javac -cp "libs/*:." *.java
java -cp "libs/*:." Main
