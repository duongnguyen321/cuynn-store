# Suggested Development Commands

## Backend (Spring Boot)
```bash
cd backend

# Start development server
mvn spring-boot:run

# Build project
mvn clean package

# Run tests
mvn test

# Access H2 Console (when running)
# URL: http://localhost:8080/h2-console
# JDBC URL: jdbc:h2:file:./zmen
# Username: sa
# Password: (empty)
```

## Frontend Development

### Admin Dashboard
```bash
cd admin

# Install dependencies
pnpm install

# Start development server
pnpm dev

# Build for production
pnpm build

# Lint code
pnpm lint

# Preview production build
pnpm preview
```

### Store Frontend
```bash
cd store

# Install dependencies
pnpm install

# Start development server
pnpm dev

# Build for production
pnpm build

# Lint code
pnpm lint

# Preview production build
pnpm preview
```

## Full Stack Development
```bash
# Start backend (terminal 1)
cd backend && mvn spring-boot:run

# Start admin (terminal 2)
cd admin && pnpm dev

# Start store (terminal 3)
cd store && pnpm dev
```

## Database Management
- Database auto-initializes on first startup
- Sample data created via `DataInitializer.java`
- H2 Console available at: http://localhost:8080/h2-console