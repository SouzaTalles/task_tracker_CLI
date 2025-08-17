# Rastreador de Tarefas CLI

Um simples **Rastreador de Tarefas via Linha de Comando (CLI)** desenvolvido em Java.  
Este projeto permite criar, atualizar, excluir e gerenciar suas tarefas diretamente do terminal.  
As tarefas são armazenadas em um arquivo JSON local, garantindo persistência entre as execuções.

---

## ✨ Funcionalidades
- Adicionar novas tarefas
- Atualizar tarefas existentes
- Excluir tarefas
- Marcar tarefas como **em andamento** ou **concluídas**
- Listar todas as tarefas
- Listar tarefas por status:
    - ✅ Concluídas
    - ⏳ Em Andamento
    - 📝 A Fazer

---

## 📂 Propriedades da Tarefa
Cada tarefa possui as seguintes propriedades:
- **id**: Identificador único
- **description**: Breve descrição da tarefa
- **status**: Um dos valores `todo`, `in-progress` ou `done`
- **createdAt**: Data e hora em que a tarefa foi criada
- **updatedAt**: Data e hora em que a tarefa foi atualizada pela última vez

---

## 🚀 Começando

### Pré-requisitos
- [Java 17+](https://adoptium.net/) instalado
- Um terminal ou prompt de comando

### Instalação
1. Clone o repositório:
   ```bash
   git clone https://github.com/SouzaTalles/task_tracker_CLI.git
   cd task-tracker-cli
   ```
2. Compile o projeto:
   ```bash
   javac Main.java
   ```
3. Execute o CLI:
   ```bash
   java Main <comando> [argumentos]
   ```

---

## 🛠️ Uso

### Adicionar uma nova tarefa
```bash
java Main add [argumentos]
```

### Atualizar uma tarefa
```bash
java Main update [id] [argumentos]
```

### Excluir uma tarefa
```bash
java Main delete [id]
```

### Marcar uma tarefa como em andamento
```bash
java Main mark-in-progress [id]
```

### Marcar uma tarefa como concluída
```bash
java Main mark-done [id]
```

### Listar todas as tarefas
```bash
java Main list
```

### Listar tarefas por status
```bash
java Main list done
java Main list todo
java Main list in-progress
```

### Ajuda
``` bash
java Main help
```

---

## 📁 Armazenamento de Dados
- As tarefas são armazenadas em um arquivo local chamado `tasks.json` no diretório do projeto.
- O arquivo será criado automaticamente caso não exista.

---

## ⚠️ Tratamento de Erros
- IDs inválidos retornam uma mensagem clara (ex.: `[ERROR] Nenhuma tarefa encontrada com ID 5`).
- Argumentos ausentes ou inválidos exibem instruções de uso.

---

## 📌 Melhorias Futuras
- Adicionar busca por palavra-chave
- Adicionar ordenação por `createdAt` ou `status`
- Adicionar testes unitários

---

## 🧑‍💻 Autor
Desenvolvido por **Talles Souza** como um projeto de prática para aprender manipulação de arquivos, entrada do usuário e aplicações CLI em Java. 

## URL do Projeto 
[Roadmap project](https://roadmap.sh/projects/task-tracker)
