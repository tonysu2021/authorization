# # First Stage
FROM node:14.5.0-alpine3.10 as builder

# # Workspace path
WORKDIR /usr/app

# Copy package.json
COPY ./package*.json ./

# Install dependencies
RUN npm install
# Copy Othor file
COPY ./ ./
COPY ./src ./src

RUN npm run build

# Second Stage
FROM nginx:1.19.2

# Front-end main program
COPY --from=builder /usr/app/dist /usr/share/nginx/html

# Nginx Configuration
COPY ./deploy/nginx/nginx.conf /etc/nginx/nginx.conf
COPY ./deploy/nginx/conf.d/default.conf /etc/nginx/conf.d/default.conf