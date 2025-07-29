# Task Completion Checklist

## When completing development tasks, always:

### Backend Changes
1. **Build & Test**
   ```bash
   cd backend
   mvn clean test
   mvn clean package
   ```

2. **Start Server** (verify no errors)
   ```bash
   mvn spring-boot:run
   ```

3. **API Testing** (if applicable)
   - Test endpoints via Swagger UI: http://localhost:8080/swagger-ui/
   - Verify database changes in H2 Console: http://localhost:8080/h2-console

### Frontend Changes
1. **Lint Code**
   ```bash
   # In admin/ or store/
   pnpm lint
   ```

2. **Build Production**
   ```bash
   pnpm build
   ```

3. **Development Testing**
   ```bash
   pnpm dev
   ```

### Database Changes
- Database auto-migrates via `hibernate.ddl-auto: update`
- Check H2 console for schema changes
- Verify DataInitializer still works correctly

### Code Quality
- Follow existing code patterns and conventions
- Use consistent naming and structure
- Ensure proper error handling
- Add appropriate logging where needed

### Integration Testing
- Test full stack: backend + frontend communication
- Verify API calls work correctly
- Check authentication flows
- Test CORS configuration if needed