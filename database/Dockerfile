# Parent
# ------------------------------------------------------------
FROM postgres:12-alpine

#set timeZone indonesia
#------------------------------------------------
ENV TZ=Asia/Jakarta

# Set environment for this images
# ------------------------------------------------------------
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD postgres
ENV POSTGRES_DB library_cosmart

# initdb
# ------------------------------------------------------------
COPY /initdb/* /docker-entrypoint-initdb.d/

#docker run -d --name financial-core-db -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=financial_core -p 5432:5432 financial-core-db:latest -c log_statement=all