# Code Structure

## Root Directory
```
cuynn-store/
├── admin/          # Admin dashboard React app
├── backend/        # Spring Boot API server
└── store/          # Customer-facing React app
```

## Backend Structure (`backend/`)
```
backend/
├── src/main/java/com/zmen/backend/
│   ├── config/           # Security, Swagger, DataInitializer
│   ├── controller/       # REST API controllers
│   ├── dto/             # Data Transfer Objects
│   ├── entity/          # JPA entities
│   ├── repository/      # JPA repositories
│   ├── service/         # Business logic services
│   └── ZmenBackendApplication.java
├── src/main/resources/
│   └── application.yml  # Spring configuration
└── pom.xml             # Maven dependencies
```

## Frontend Structure (Both `admin/` & `store/`)
```
src/
├── components/
│   └── ui/             # shadcn/ui components
├── hooks/              # Custom React hooks
├── lib/                # Utility functions
├── services/           # API service layer
├── App.jsx             # Main app component
├── main.jsx           # React entry point
└── index.css          # Global styles
```

## Key Entities
- **User**: Authentication with roles (ADMIN, EMPLOYEE, CUSTOMER)
- **Product**: Catalog items with pricing, categories, inventory
- **Cart/CartItem**: Shopping cart functionality
- **Order/OrderItem**: Order management system