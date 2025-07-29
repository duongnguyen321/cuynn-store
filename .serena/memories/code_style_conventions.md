# Code Style & Conventions

## Backend (Java/Spring Boot)
- **Language Level**: Java 11
- **Naming**: CamelCase for classes, camelCase for methods/variables
- **Package Structure**: `com.zmen.backend.*`
- **Annotations**: Heavy use of Spring annotations (@RestController, @Service, @Entity, etc.)
- **Configuration**: YAML format (application.yml)
- **Security**: JWT-based authentication
- **API Endpoints**: REST with `/api/` prefix

### Entity Conventions
- JPA entities with standard annotations (@Entity, @Id, @GeneratedValue)
- Bidirectional relationships where appropriate
- Enums for status/role types (User.Role, Order.OrderStatus, etc.)

## Frontend (React/JavaScript)
- **Language**: Modern JavaScript (ES2022+)
- **Component Style**: Functional components with hooks
- **File Extensions**: `.jsx` for React components, `.js` for utilities
- **Naming**: PascalCase for components, camelCase for functions/variables
- **Styling**: Tailwind CSS utility classes
- **Component Structure**: shadcn/ui pattern with forwarded refs

### React Patterns
- Custom hooks for shared logic (`use-mobile.js`)
- Context API for theme management
- Form handling with React Hook Form + Zod
- Consistent use of Radix UI primitives

## Styling Conventions
- **CSS Framework**: Tailwind CSS 4.x
- **Component Variants**: class-variance-authority (CVA)
- **Theme**: Light/dark mode support via next-themes
- **Icons**: Lucide React icon library
- **Responsive**: Mobile-first approach

## File Organization
- UI components in `components/ui/` directory
- Custom hooks in `hooks/` directory
- Utilities in `lib/` directory
- API services in `services/` directory