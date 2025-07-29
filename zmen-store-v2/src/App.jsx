import React, { useState, useEffect, createContext, useContext } from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { 
  ShoppingCart, Search, User, Menu, X, Star, Heart, 
  ChevronDown, Sun, Moon, Plus, Minus 
} from 'lucide-react';
import { Button } from '@/components/ui/button.jsx';
import { Input } from '@/components/ui/input.jsx';
import { Badge } from '@/components/ui/badge.jsx';
import { Card, CardContent, CardFooter } from '@/components/ui/card.jsx';
import { motion, AnimatePresence } from 'framer-motion';
import ApiService from './services/api.js';
import './App.css';

// Import images
import clothingDisplay1 from './assets/clothing_display_1.jpg';
import clothingDisplay2 from './assets/clothing_display_2.webp';
import clothingDisplay3 from './assets/clothing_display_3.jpg';
import clothingDisplay4 from './assets/clothing_display_4.jpg';

// Theme Context
const ThemeContext = createContext();

const useTheme = () => {
  const context = useContext(ThemeContext);
  if (!context) {
    throw new Error('useTheme must be used within a ThemeProvider');
  }
  return context;
};

const ThemeProvider = ({ children }) => {
  const [theme, setTheme] = useState(() => {
    const savedTheme = localStorage.getItem('zmen-theme');
    return savedTheme || 'light';
  });

  useEffect(() => {
    localStorage.setItem('zmen-theme', theme);
    document.documentElement.classList.toggle('dark', theme === 'dark');
  }, [theme]);

  const toggleTheme = () => {
    setTheme(prev => prev === 'light' ? 'dark' : 'light');
  };

  return (
    <ThemeContext.Provider value={{ theme, toggleTheme }}>
      {children}
    </ThemeContext.Provider>
  );
};

const Header = ({ cartCount, onCartClick }) => {
  const [searchQuery, setSearchQuery] = useState('');
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const { theme, toggleTheme } = useTheme();

  return (
    <header className="bg-background/95 backdrop-blur-md border-b border-border sticky top-0 z-50">
      <div className="container mx-auto px-4 py-4">
        <div className="flex items-center justify-between">
          {/* Logo */}
          <Link to="/" className="text-3xl font-black text-foreground tracking-tight">
            ZMEN
          </Link>

          {/* Navigation - Desktop */}
          <nav className="hidden lg:flex items-center space-x-8">
            <Link to="/" className="text-muted-foreground hover:text-foreground transition-colors font-medium">
              Trang chủ
            </Link>
            <div className="relative group">
              <button className="text-muted-foreground hover:text-foreground transition-colors flex items-center font-medium">
                Sản phẩm <ChevronDown className="ml-1 h-4 w-4" />
              </button>
              <div className="absolute top-full left-0 bg-popover shadow-xl rounded-lg py-3 w-56 opacity-0 invisible group-hover:opacity-100 group-hover:visible transition-all duration-200 border border-border">
                <Link to="/products/shirts" className="block px-4 py-3 text-muted-foreground hover:text-foreground hover:bg-accent transition-colors">
                  Áo sơ mi
                </Link>
                <Link to="/products/t-shirts" className="block px-4 py-3 text-muted-foreground hover:text-foreground hover:bg-accent transition-colors">
                  Áo thun
                </Link>
                <Link to="/products/pants" className="block px-4 py-3 text-muted-foreground hover:text-foreground hover:bg-accent transition-colors">
                  Quần
                </Link>
                <Link to="/products/suits" className="block px-4 py-3 text-muted-foreground hover:text-foreground hover:bg-accent transition-colors">
                  Vest & Blazer
                </Link>
              </div>
            </div>
            <Link to="/about" className="text-muted-foreground hover:text-foreground transition-colors font-medium">
              Về chúng tôi
            </Link>
            <Link to="/contact" className="text-muted-foreground hover:text-foreground transition-colors font-medium">
              Liên hệ
            </Link>
          </nav>

          {/* Search Bar - Desktop */}
          <div className="hidden md:flex items-center flex-1 max-w-md mx-8">
            <div className="relative w-full">
              <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-muted-foreground h-4 w-4" />
              <Input
                type="text"
                placeholder="Tìm kiếm sản phẩm..."
                value={searchQuery}
                onChange={(e) => setSearchQuery(e.target.value)}
                className="pl-10"
              />
            </div>
          </div>

          {/* Right Actions */}
          <div className="flex items-center space-x-4">
            {/* Theme Toggle */}
            <Button
              variant="ghost"
              size="sm"
              onClick={toggleTheme}
              className="p-2"
            >
              {theme === 'light' ? (
                <Moon className="h-5 w-5" />
              ) : (
                <Sun className="h-5 w-5" />
              )}
            </Button>

            {/* User Account */}
            <Button variant="ghost" size="sm" className="hidden md:flex p-2">
              <User className="h-5 w-5" />
            </Button>

            {/* Favorites */}
            <Button variant="ghost" size="sm" className="hidden md:flex p-2">
              <Heart className="h-5 w-5" />
            </Button>

            {/* Shopping Cart */}
            <Button
              variant="ghost"
              size="sm"
              onClick={onCartClick}
              className="relative p-2"
            >
              <ShoppingCart className="h-5 w-5" />
              {cartCount > 0 && (
                <Badge className="absolute -top-1 -right-1 h-5 w-5 flex items-center justify-center text-xs">
                  {cartCount}
                </Badge>
              )}
            </Button>

            {/* Mobile Menu Toggle */}
            <Button
              variant="ghost"
              size="sm"
              onClick={() => setIsMenuOpen(!isMenuOpen)}
              className="lg:hidden p-2"
            >
              {isMenuOpen ? (
                <X className="h-5 w-5" />
              ) : (
                <Menu className="h-5 w-5" />
              )}
            </Button>
          </div>
        </div>

        {/* Mobile Menu */}
        <AnimatePresence>
          {isMenuOpen && (
            <motion.div
              initial={{ opacity: 0, height: 0 }}
              animate={{ opacity: 1, height: 'auto' }}
              exit={{ opacity: 0, height: 0 }}
              className="lg:hidden mt-4 border-t border-border pt-4"
            >
              {/* Mobile Search */}
              <div className="relative mb-4">
                <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-muted-foreground h-4 w-4" />
                <Input
                  type="text"
                  placeholder="Tìm kiếm sản phẩm..."
                  value={searchQuery}
                  onChange={(e) => setSearchQuery(e.target.value)}
                  className="pl-10"
                />
              </div>

              {/* Mobile Navigation */}
              <nav className="space-y-3">
                <Link to="/" className="block text-muted-foreground hover:text-foreground font-medium">
                  Trang chủ
                </Link>
                <Link to="/products" className="block text-muted-foreground hover:text-foreground font-medium">
                  Sản phẩm
                </Link>
                <Link to="/about" className="block text-muted-foreground hover:text-foreground font-medium">
                  Về chúng tôi
                </Link>
                <Link to="/contact" className="block text-muted-foreground hover:text-foreground font-medium">
                  Liên hệ
                </Link>
                <div className="flex items-center space-x-4 pt-3 border-t border-border">
                  <Button variant="ghost" size="sm" className="p-2">
                    <User className="h-5 w-5" />
                  </Button>
                  <Button variant="ghost" size="sm" className="p-2">
                    <Heart className="h-5 w-5" />
                  </Button>
                </div>
              </nav>
            </motion.div>
          )}
        </AnimatePresence>
      </div>
    </header>
  );
};

const HeroSection = () => {
  return (
    <section className="relative h-screen flex items-center justify-center overflow-hidden">
      {/* Background Image */}
      <div className="absolute inset-0">
        <img
          src={clothingDisplay1}
          alt="ZMEN Fashion"
          className="w-full h-full object-cover"
        />
        <div className="absolute inset-0 bg-black/50"></div>
      </div>

      {/* Content */}
      <div className="relative z-10 text-center text-white max-w-4xl mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 30 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
        >
          <h1 className="text-6xl md:text-8xl font-black mb-6 tracking-tight">
            THỜI TRANG NAM
          </h1>
          <h2 className="text-3xl md:text-5xl font-light mb-8 text-green-400">
            Phong Cách Hiện Đại
          </h2>
          <p className="text-xl md:text-2xl mb-12 text-gray-200 max-w-2xl mx-auto leading-relaxed">
            Khám phá bộ sưu tập quần áo nam cao cấp, thiết kế tinh tế và chất lượng vượt trội làm nên phong cách của bạn.
          </p>
          
          <div className="flex flex-col sm:flex-row gap-4 justify-center">
            <Button size="lg" className="bg-white text-black hover:bg-gray-100 font-semibold px-8 py-4 text-lg">
              Mua ngay
            </Button>
            <Button 
              variant="outline" 
              size="lg" 
              className="border-white text-white hover:bg-white hover:text-black font-semibold px-8 py-4 text-lg"
            >
              Xem bộ sưu tập
            </Button>
          </div>
        </motion.div>
      </div>

      {/* Scroll Indicator */}
      <motion.div
        className="absolute bottom-8 left-1/2 transform -translate-x-1/2"
        animate={{ y: [0, 10, 0] }}
        transition={{ repeat: Infinity, duration: 2 }}
      >
        <div className="w-6 h-10 border-2 border-white rounded-full flex justify-center">
          <div className="w-1 h-3 bg-white rounded-full mt-2"></div>
        </div>
      </motion.div>
    </section>
  );
};

const FeaturedProducts = ({ onAddToCart }) => {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProducts = async () => {
      try {
        setLoading(true);
        const response = await ApiService.getFeaturedProducts();
        // Spring Boot API trả về array trực tiếp, không có wrapper success/data
        if (Array.isArray(response)) {
          setProducts(response);
        } else {
          setError('Không thể tải sản phẩm');
        }
      } catch (err) {
        console.error('Error fetching products:', err);
        setError('Lỗi kết nối API');
      } finally {
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  if (loading) {
    return (
      <section className="py-20 bg-background">
        <div className="container mx-auto px-4">
          <div className="text-center">
            <div className="loading-spinner mx-auto"></div>
            <p className="mt-4 text-muted-foreground">Đang tải sản phẩm...</p>
          </div>
        </div>
      </section>
    );
  }

  if (error) {
    return (
      <section className="py-20 bg-background">
        <div className="container mx-auto px-4">
          <div className="text-center">
            <p className="text-red-500">{error}</p>
          </div>
        </div>
      </section>
    );
  }

  return (
    <section className="py-20 bg-background">
      <div className="container mx-auto px-4">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          whileInView={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.6 }}
          className="text-center mb-16"
        >
          <h2 className="text-4xl md:text-5xl font-black text-foreground mb-4">
            Sản Phẩm Nổi Bật
          </h2>
          <p className="text-xl text-muted-foreground max-w-2xl mx-auto">
            Khám phá những sản phẩm được yêu thích nhất với chất lượng cao và thiết kế thời trang
          </p>
        </motion.div>

        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {products.map((product, index) => (
            <motion.div
              key={product.id}
              initial={{ opacity: 0, y: 30 }}
              whileInView={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.6, delay: index * 0.1 }}
            >
              <Card className="group cursor-pointer hover:shadow-xl transition-all duration-300">
                <div className="relative overflow-hidden">
                  <img
                    src={product.image}
                    alt={product.name}
                    className="w-full h-80 object-cover group-hover:scale-105 transition-transform duration-500"
                  />
                  
                  {/* Badges */}
                  <div className="absolute top-3 left-3 flex flex-col gap-2">
                    {product.discount > 0 && (
                      <Badge className="bg-red-500 text-white font-semibold">
                        -{product.discount}%
                      </Badge>
                    )}
                    {product.isNew && (
                      <Badge className="bg-green-500 text-white font-semibold">
                        Mới
                      </Badge>
                    )}
                    {product.isBestSeller && (
                      <Badge className="bg-orange-500 text-white font-semibold">
                        Bán chạy
                      </Badge>
                    )}
                  </div>

                  {/* Favorite Button */}
                  <Button
                    variant="ghost"
                    size="sm"
                    className="absolute top-3 right-3 p-2 bg-white/80 hover:bg-white opacity-0 group-hover:opacity-100 transition-opacity"
                  >
                    <Heart className="h-4 w-4" />
                  </Button>
                </div>

                <CardContent className="p-6">
                  <h3 className="font-bold text-lg text-foreground mb-2 line-clamp-2">
                    {product.name}
                  </h3>
                  
                  {/* Rating */}
                  <div className="flex items-center gap-2 mb-3">
                    <div className="flex items-center">
                      {[...Array(5)].map((_, i) => (
                        <Star
                          key={i}
                          className={`h-4 w-4 ${
                            i < Math.floor(product.rating)
                              ? 'text-yellow-400 fill-current'
                              : 'text-gray-300'
                          }`}
                        />
                      ))}
                    </div>
                    <span className="text-sm text-muted-foreground">
                      {product.rating} ({product.reviews})
                    </span>
                  </div>

                  {/* Price */}
                  <div className="flex items-center gap-2 mb-4">
                    <span className="text-2xl font-bold text-foreground">
                      {product.price.toLocaleString('vi-VN')}đ
                    </span>
                    {product.originalPrice && (
                      <span className="text-lg text-muted-foreground line-through">
                        {product.originalPrice.toLocaleString('vi-VN')}đ
                      </span>
                    )}
                  </div>
                </CardContent>

                <CardFooter className="p-6 pt-0">
                  <Button
                    onClick={() => onAddToCart(product)}
                    className="w-full font-semibold"
                    disabled={product.stockQuantity === 0}
                  >
                    {product.stockQuantity === 0 ? 'Hết hàng' : 'Thêm vào giỏ hàng'}
                  </Button>
                </CardFooter>
              </Card>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};

const ServicesSection = () => {
  const services = [
    {
      icon: "🚚",
      title: "Giao hàng miễn phí",
      description: "Miễn phí giao hàng cho đơn hàng trên 500.000đ"
    },
    {
      icon: "💎",
      title: "Chất lượng cao cấp",
      description: "Sản phẩm được tuyển chọn kỹ lưỡng từ các thương hiệu uy tín"
    },
    {
      icon: "🔄",
      title: "Đổi trả dễ dàng",
      description: "Đổi trả trong vòng 30 ngày nếu không hài lòng"
    },
    {
      icon: "🎯",
      title: "Hỗ trợ 24/7",
      description: "Đội ngũ tư vấn chuyên nghiệp luôn sẵn sàng hỗ trợ"
    }
  ];

  return (
    <section className="py-20 bg-muted/30">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-8">
          {services.map((service, index) => (
            <motion.div
              key={index}
              initial={{ opacity: 0, y: 20 }}
              whileInView={{ opacity: 1, y: 0 }}
              transition={{ duration: 0.6, delay: index * 0.1 }}
              className="text-center"
            >
              <div className="text-4xl mb-4">{service.icon}</div>
              <h3 className="text-xl font-bold text-foreground mb-2">
                {service.title}
              </h3>
              <p className="text-muted-foreground">
                {service.description}
              </p>
            </motion.div>
          ))}
        </div>
      </div>
    </section>
  );
};

const Footer = () => {
  return (
    <footer className="bg-foreground text-background py-16">
      <div className="container mx-auto px-4">
        <div className="grid grid-cols-1 md:grid-cols-4 gap-8">
          {/* Company Info */}
          <div>
            <h3 className="text-2xl font-black mb-4">ZMEN</h3>
            <p className="text-gray-300 mb-4">
              Thương hiệu thời trang nam hàng đầu Việt Nam, mang đến phong cách hiện đại và chất lượng cao cấp.
            </p>
            <div className="flex space-x-4">
              <Button variant="ghost" size="sm" className="text-background hover:text-green-400">
                Facebook
              </Button>
              <Button variant="ghost" size="sm" className="text-background hover:text-green-400">
                Instagram
              </Button>
            </div>
          </div>

          {/* Quick Links */}
          <div>
            <h4 className="text-lg font-bold mb-4">Liên kết</h4>
            <ul className="space-y-2">
              <li><Link to="/" className="text-gray-300 hover:text-background transition-colors">Trang chủ</Link></li>
              <li><Link to="/products" className="text-gray-300 hover:text-background transition-colors">Sản phẩm</Link></li>
              <li><Link to="/about" className="text-gray-300 hover:text-background transition-colors">Về chúng tôi</Link></li>
              <li><Link to="/contact" className="text-gray-300 hover:text-background transition-colors">Liên hệ</Link></li>
            </ul>
          </div>

          {/* Customer Service */}
          <div>
            <h4 className="text-lg font-bold mb-4">Hỗ trợ</h4>
            <ul className="space-y-2">
              <li><Link to="/size-guide" className="text-gray-300 hover:text-background transition-colors">Hướng dẫn chọn size</Link></li>
              <li><Link to="/shipping" className="text-gray-300 hover:text-background transition-colors">Chính sách giao hàng</Link></li>
              <li><Link to="/returns" className="text-gray-300 hover:text-background transition-colors">Đổi trả</Link></li>
              <li><Link to="/faq" className="text-gray-300 hover:text-background transition-colors">FAQ</Link></li>
            </ul>
          </div>

          {/* Contact Info */}
          <div>
            <h4 className="text-lg font-bold mb-4">Liên hệ</h4>
            <div className="space-y-2 text-gray-300">
              <p>📍 123 Đường ABC, Quận 1, TP.HCM</p>
              <p>📞 0123 456 789</p>
              <p>✉️ info@zmen.vn</p>
            </div>
          </div>
        </div>

        <div className="border-t border-gray-700 mt-12 pt-8 text-center text-gray-300">
          <p>&copy; 2024 ZMEN. Tất cả quyền được bảo lưu.</p>
        </div>
      </div>
    </footer>
  );
};

const App = () => {
  const [cartItems, setCartItems] = useState([]);
  const [cartCount, setCartCount] = useState(0);
  const [loading, setLoading] = useState(false);

  // Load cart on component mount
  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {
    try {
      const response = await ApiService.getCart();
      if (response.success) {
        setCartItems(response.data.items);
        setCartCount(response.data.totalItems);
      }
    } catch (error) {
      console.error('Error loading cart:', error);
    }
  };

  const handleAddToCart = async (product) => {
    try {
      setLoading(true);
      const response = await ApiService.addToCart(product.id, 1);
      if (response.success) {
        // Reload cart to get updated data
        await loadCart();
        
        // Show success message (you can add a toast notification here)
        console.log('Product added to cart successfully');
      }
    } catch (error) {
      console.error('Error adding to cart:', error);
      // Show error message (you can add a toast notification here)
    } finally {
      setLoading(false);
    }
  };

  const handleCartClick = async () => {
    // For now, just log cart items. Later you can open a cart modal/page
    console.log('Cart clicked', cartItems);
    
    // Reload cart to show latest data
    await loadCart();
  };

  return (
    <ThemeProvider>
      <Router>
        <div className="min-h-screen bg-background text-foreground">
          <Header 
            cartCount={cartCount} 
            onCartClick={handleCartClick}
          />
          
          <main>
            <Routes>
              <Route path="/" element={
                <>
                  <HeroSection />
                  <FeaturedProducts onAddToCart={handleAddToCart} />
                  <ServicesSection />
                </>
              } />
            </Routes>
          </main>

          <Footer />
          
          {/* Loading overlay */}
          {loading && (
            <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
              <div className="loading-spinner"></div>
            </div>
          )}
        </div>
      </Router>
    </ThemeProvider>
  );
};

export default App;

