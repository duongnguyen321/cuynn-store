# Phân Tích Thiết Kế Nike - Tham Khảo Cho ZMEN

## Đặc Điểm Thiết Kế Chính

### 1. Màu Sắc & Phong Cách
- **Màu chủ đạo**: Trắng, đen, xám
- **Màu nhấn**: Vàng neon (Nike logo), màu sắc sản phẩm đa dạng
- **Background**: Chủ yếu trắng với các section có màu nền khác nhau
- **Contrast**: Cao, dễ đọc

### 2. Typography
- **Font chính**: Sans-serif, bold, hiện đại
- **Hierarchy**: Rõ ràng với các cấp độ khác nhau
- **Slogan**: "Just Do It" - font lớn, bold
- **Product names**: Font vừa, clean

### 3. Layout & Grid
- **Hero section**: Full-width video/image với text overlay
- **Product grid**: 3-5 columns, spacing đều
- **Card design**: Clean, minimal với hover effects
- **Navigation**: Sticky header, dropdown menus

### 4. Visual Elements
- **Product cards**: 
  - Hình ảnh lớn, chất lượng cao
  - Background màu pastel nhẹ nhàng
  - Tên sản phẩm ở dưới
  - Button "Shop" với border radius
- **Hero banners**: 
  - Full-width images/videos
  - Text overlay với contrast cao
  - CTA buttons nổi bật

### 5. Interactive Elements
- **Buttons**: 
  - Primary: Đen với text trắng
  - Secondary: Trắng với border đen
  - Hover effects: Subtle transitions
- **Navigation**: 
  - Mega menus với categories
  - Search bar prominent
  - Icons: Heart (favorites), Bag (cart)

### 6. Mobile Responsiveness
- Grid adapts từ desktop xuống mobile
- Touch-friendly button sizes
- Hamburger menu cho mobile

## Ứng Dụng Cho ZMEN

### Theme Colors
```css
/* Light Theme */
--primary: #000000
--secondary: #ffffff  
--accent: #7ed321 (green thay vì yellow)
--text: #111111
--text-secondary: #757575
--background: #ffffff
--surface: #f7f7f7

/* Dark Theme */
--primary: #ffffff
--secondary: #000000
--accent: #7ed321
--text: #ffffff
--text-secondary: #b3b3b3
--background: #111111
--surface: #1e1e1e
```

### Layout Structure
1. **Header**: Logo, Navigation, Search, User icons
2. **Hero**: Large banner với sản phẩm nổi bật
3. **Categories**: Grid layout với hình ảnh đẹp
4. **Featured Products**: Horizontal scroll hoặc grid
5. **Footer**: Links, social media, newsletter

### Key Features To Implement
- Dark/Light theme toggle
- Smooth transitions và animations
- High-quality product images
- Clean typography hierarchy
- Responsive grid system
- Hover effects trên product cards
- Sticky navigation
- Search functionality
- Shopping cart với animation

