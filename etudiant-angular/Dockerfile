FROM node:16.20.2 as builder

WORKDIR /app

COPY . /app

RUN npm uninstall -D angular-cli --legacy-peer-deps

RUN npm install --force

FROM nginx:latest

COPY --from=builder /app/dist/ /usr/share/nginx/html

EXPOSE 4200