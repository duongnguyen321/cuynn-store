const API_BASE_URL = 'http://localhost:8080/api';

class ApiService {
  async request(endpoint, options = {}) {
    const url = `${API_BASE_URL}${endpoint}`;
    const config = {
      headers: {
        'Content-Type': 'application/json',
      },
      credentials: 'include', // Include cookies for session
      ...options,
    };

    if (config.body && typeof config.body === 'object') {
      config.body = JSON.stringify(config.body);
    }

    try {
      const response = await fetch(url, config);
      
      // Handle non-JSON responses
      const contentType = response.headers.get('content-type');
      let data;
      if (contentType && contentType.includes('application/json')) {
        data = await response.json();
      } else {
        data = await response.text();
      }

      if (!response.ok) {
        throw new Error(data.message || data || 'API request failed');
      }

      return data;
    } catch (error) {
      console.error('API Error:', error);
      throw error;
    }
  }

  // Product APIs
  async getProducts(params = {}) {
    const queryString = new URLSearchParams(params).toString();
    const endpoint = queryString ? `/products?${queryString}` : '/products';
    return this.request(endpoint);
  }

  async getProduct(id) {
    return this.request(`/products/${id}`);
  }

  async getFeaturedProducts() {
    return this.request('/products/featured');
  }

  async getBestSellers() {
    return this.request('/products/bestsellers');
  }

  async getNewProducts() {
    return this.request('/products/new');
  }

  async searchProducts(keyword, params = {}) {
    const queryString = new URLSearchParams({ keyword, ...params }).toString();
    return this.request(`/products/search?${queryString}`);
  }

  async getCategories() {
    return this.request('/products/categories');
  }

  async getBrands() {
    return this.request('/products/brands');
  }

  async getProductsByCategory(category, params = {}) {
    const queryString = new URLSearchParams(params).toString();
    const endpoint = queryString ? `/products/category/${category}?${queryString}` : `/products/category/${category}`;
    return this.request(endpoint);
  }

  // Cart APIs
  async getCart() {
    return this.request('/cart');
  }

  async addToCart(productId, quantity = 1, size = null, color = null) {
    return this.request('/cart/add', {
      method: 'POST',
      body: { productId, quantity, size, color },
    });
  }

  async updateCartItem(itemId, quantity) {
    return this.request(`/cart/update/${itemId}?quantity=${quantity}`, {
      method: 'PUT',
    });
  }

  async removeFromCart(itemId) {
    return this.request(`/cart/remove/${itemId}`, {
      method: 'DELETE',
    });
  }

  async clearCart() {
    return this.request('/cart/clear', {
      method: 'DELETE',
    });
  }

  // Order APIs
  async checkout(orderData) {
    return this.request('/orders/checkout', {
      method: 'POST',
      body: orderData,
    });
  }

  async getOrders(params = {}) {
    const queryString = new URLSearchParams(params).toString();
    const endpoint = queryString ? `/orders?${queryString}` : '/orders';
    return this.request(endpoint);
  }

  async getOrder(id) {
    return this.request(`/orders/${id}`);
  }

  async getOrderByNumber(orderNumber) {
    return this.request(`/orders/number/${orderNumber}`);
  }
}

export default new ApiService();

