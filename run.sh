#!/bin/bash

echo "🚀 Construindo e executando o sistema..."

# Build da aplicação
echo "📦 Construindo aplicação..."
mvn clean package -DskipTests

# Executar docker compose
echo "🐳 Iniciando containers..."
docker-compose up --build

echo "✅ Sistema iniciado com sucesso!"
echo "📊 Acesse:"
echo "   - API: http://localhost:8080"
echo "   - RabbitMQ: http://localhost:15672 (guest/guest)"
