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
* Nunca exponha chaves de API publicamente.
* Recomenda-se uso de variáveis de ambiente (.env).
