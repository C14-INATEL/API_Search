# 🔐 Sistema de Monitoramento de Vazamento de Senhas

## 📌 Visão Geral

O **Sistema de Monitoramento de Vazamento de Senhas** é uma aplicação que permite aos usuários verificar se suas contas digitais (emails, usernames e credenciais) foram comprometidas em vazamentos de dados conhecidos.

A plataforma integra APIs reais de segurança e apresenta **alertas de risco** para análise de segurança.

---

## 🎯 Objetivo

Permitir que usuários monitorem suas contas digitais e recebam notificações sobre possíveis vazamentos, ajudando na prevenção de ataques e no fortalecimento da segurança.

---

## 🧱 Arquitetura do Sistema

### Backend

* Node.js ou Java

### Banco de Dados

* PostgreSQL ou MySQL

---

## 🔄 Fluxo do Sistema

```
Usuário cadastra email
        ↓
Backend consulta APIs externas
        ↓
Resultados retornam
        ↓
Sistema salva no banco
        ↓
Dashboard apresenta alertas
```

---

## 🗄️ Estrutura do Banco de Dados

### 👤 Tabela: usuarios

* id
* nome
* email
* senha_hash
* data_cadastro
* data_atualizacao
* token

### 📧 Tabela: contas_monitoradas

* id
* usuario_id
* email
* account_monitored
* status_risco
* hash_senha

### 🚨 Tabela: vazamentos

* id
* site
* data_vazamento
* registros

### 📬 Tabela: emails_vazados

* id
* email
* vazamento_id

### ⚠️ Tabela: alertas

* id
* usuario_id
* email
* vazamento
* nivel_risco
* data_alerta

---

## 🔌 APIs Utilizadas

### 1. LeakCheck

📎 https://leakcheck.io/docs

**Função:**
Verificar se um email foi exposto em vazamentos conhecidos.

**Endpoint:**

```
GET /users/{id}/dashboard
```

**Campos utilizados:**

* Name → Nome do serviço afetado
* BreachDate → Data do vazamento
* PwnCount → Número de contas afetadas
* DataClasses → Tipos de dados vazados
---

### 2. LeakCheck

📎 https://leakcheck.io/docs

**Função:**
Detectar credenciais completas vazadas (email + senha).

**Exemplo de resposta:**

```json
{
  "success": true,
  "found": 1,
  "result": [
    {
      "email": "user@gmail.com",
      "password": "123456",
      "source": "LinkedIn",
      "date": "2021"
    }
  ]
}
```

---

## 📊 Dashboard

### Indicadores:

* Total de contas monitoradas
* Contas comprometidas
* Nível de risco
* Security Score

---

## 🛡️ Security Score

O sistema calcula uma pontuação de segurança do usuário.

**Exemplo:**

```			
Security Score: 75/100
```

### Penalidades:

* Email encontrado em vazamento → -25
* Senha vazada → -50
* Senha fraca → -25

---

## 🔐 Boas Práticas de Segurança

* Utilizar hashing seguro (bcrypt recomendado)
* Nunca armazenar senhas em texto plano
* Implementar autenticação com token (JWT)
* Utilizar HTTP em todas as comunicações
* Rotacionar chaves de API regularmente

---

## 🚀 Possíveis Melhorias Futuras

* Autenticação em dois fatores (2FA)
* Notificações em tempo real (email/SMS)
* Integração com mais APIs de segurança
* Monitoramento contínuo automático
* Aplicativo mobile

---

## ⚠️ Observações Importantes

* Este sistema lida com **dados sensíveis**, portanto segurança deve ser prioridade.

---

## ⚠️ Como Testar a aplicação ⚠️

A aplicação consta em Desenvolvimento, Portanto é necessário conter os seguintes requisitos para realisar os testes.

1 . Ter instalado o Postman para utilizar os métodos

---
USER
---

## Listar Usuarios Salvos

```
GET http://localhost:8080/users
```

## Salvar Novos Usuarios

```
POST http://localhost:8080/users
```

#### exemplo CopyPaste

{
"name": "Christopher",
"email": "chrislima@gmail.com",
"password": "viciadoEmClaudeIA"
}

Verificar Atualização:
```
GET http://localhost:8080/users
```

## Pesquisar Usuario Por ID

Troque {id} por algum id existente:

 ids = { 22, 23, 24 }

```
GET http://localhost:8080/users/{id}
```

## Verificar se um Usuario existe pelo ID

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
GET http://localhost:8080/users/{id}/exist
```

## Atualizar nome de uma Conta

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
PUT http://localhost:8080/users/{id}/update_name
```
#### exemplo CopyPaste

{
    "name": "Marcelo"
}

Verificar Atualização:
```
GET http://localhost:8080/users
```

## Atualizar email de uma Conta

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
PUT http://localhost:8080/users/{id}/update_email
```

#### exemplo CopyPaste

{
"email": "marcelinhoCG160@gmail.com"
}

Verificar Atualização:
```
GET http://localhost:8080/users
```
## Atualizar Password de uma Conta

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
PUT http://localhost:8080/users/{id}/update_password
```

#### exemplo CopyPaste

{
"password": "Malandro123"
}

Verificar Atualização:
```
GET http://localhost:8080/users
```
## Atualizar Token de uma Conta

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
PUT http://localhost:8080/users/{id}/update_token
```

#### exemplo CopyPaste

{
"token": "osnj35bk3b56kj46bk#5kj"
}

Verificar Atualização:
```
GET http://localhost:8080/users
```

## Deletar um Usuario por id

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
DELETE http://localhost:8080/users/{id}
```

Verificar Atualização:
```
GET http://localhost:8080/users
```

---
Accounts
---

## Listar Accounts Salvos

```
GET http://localhost:8080/users
```
## Listar Contas de um Usuario pelo ID

Troque {id} por algum id existente:

ids = { 22, 23, 24 }

```
GET http://localhost:8080/users/{id}
```

## Pesquisar Conta por ID

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
GET http://localhost:8080/accounts/{id}
```

## Verificar se Conta existe pelo ID

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
GET http://localhost:8080/accounts/{id}/exist
```

## Atualizar email de uma conta

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
PUT http://localhost:8080/accounts/{id}/update_adress
```

#### exemplo CopyPaste

{
"address": "PaulinhoCapa@gmail.com"
}

Verificar Atualização:
```
GET http://localhost:8080/accounts/{id}
```


## Atualizar Status de uma conta

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
PUT http://localhost:8080/accounts/{id}/update_status
```

#### exemplo CopyPaste

{
"status" = "LOW"
}

Verificar Atualização:
```
GET http://localhost:8080/accounts/{id}
```


## Atualizar Password de uma conta

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
PUT http://localhost:8080/accounts/{id}/update_password
```

#### exemplo CopyPaste

{
"password_hash": "Karl Marx"
}

Verificar Atualização:
```
GET http://localhost:8080/accounts/{id}
```


## Atualizar Descrição de uma conta

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
PUT http://localhost:8080/accounts/{id}/update_description
```

#### exemplo CopyPaste

{
"description": "Instagram.com"
}

Verificar Atualização:
```
GET http://localhost:8080/accounts/{id}
```


## Deletar contas Por id

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
DELETE http://localhost:8080/accounts/{id}
```
Verificar Atualização:
```
GET http://localhost:8080/accounts/{id}
```

## Deletar todas as contas de um Usuario

Troque {id} por algum id existente:

USERS:
- 22 - ID conta: 30
- 23 - ID conta: 31, 32
- 24 - ID conta: 33

```
DELETE http://localhost:8080/accounts/user/{id}
```

Verificar Atualização:
```
GET http://localhost:8080/accounts/{id}
```

---
Alert
---

Só sera possivel visualizar alertas de emails vazados, se o seu email ja foi vazado na API Leak.

Estamos utilizando somente essa API no momento pois, ela tem um plano free mais versatil, podendo realizar várias requisições,
ademais será adicionado mais API's para uma melhor qualidade do Software

No momento você pode verificar o funcionamento da verificação de vazamento, utilizando um email que foi vazado no API Leak. Para testar Faça:

Veja que não existe nenhum email vazado:

```
GET http://localhost:8080/alert
```

Crie uma conta onde ela tenha o email abaixo:

Email Vazado: test@gmail.com

Pronto, quando a conta for criada, a API irá verificar se esse email consta no banco de dados de vazamento deles, com o retorno da api o sistema 
API Search irá criar um alerta para indicar que essa conta foi vazada.

Verificar Atualização:
```
GET http://localhost:8080/alert
```



