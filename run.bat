@echo off
echo 🚀 Construindo e executando o sistema...

REM Build da aplicação
echo 📦 Construindo aplicação...
mvn clean package -DskipTests

REM Executar docker compose
echo 🐳 Iniciando containers...
docker-compose up --build

echo ✅ Sistema iniciado com sucesso!
echo 📊 Acesse:
echo    - API: http://localhost:8080
echo    - RabbitMQ: http://localhost:15672 (guest/guest)
pause
