docker run --rm --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=n2s -e POSTGRES_USER=n2s -p 5432:5432 -d postgres

docker exec -i postgres psql -U n2s < banco_public.sql
docker exec -i postgres psql -U n2s < banco_darwin.sql
docker exec -i postgres psql -U n2s < dados_guardiao.sql