# ZMEN E-commerce Store - Project Overview

## Purpose
ZMEN is a full-stack e-commerce application for selling men's clothing and fashion items. It consists of three main components:
- **Store**: Customer-facing React e-commerce website
- **Admin**: Admin dashboard for managing products, orders, users
- **Backend**: Spring Boot REST API server with H2 database

## Architecture
This is a multi-tier web application with:
- **Frontend**: 2 React applications (store + admin) using Vite
- **Backend**: Spring Boot REST API
- **Database**: H2 file-based database
- **Build Tools**: Maven (backend), pnpm (frontend)

## Main Features
- Product catalog with categories (shirts, polo, jeans, t-shirts, jackets, shorts)
- Shopping cart functionality
- User authentication with roles (ADMIN, EMPLOYEE, CUSTOMER)
- Order management system
- Admin dashboard for inventory and user management

## Development Status
The project includes pre-seeded sample data:
- 3 users (admin, employee, customer)
- 6 sample products with Vietnamese descriptions
- Database auto-initializes on startup via DataInitializer