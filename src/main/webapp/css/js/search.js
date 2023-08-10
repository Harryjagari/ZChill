const searchInput = document.querySelector('.input');
const products = document.querySelectorAll('.product-item');

searchInput.addEventListener('keyup', function() {
  const searchQuery = this.value.toLowerCase();

  products.forEach(function(product) {
    const productName = product.querySelector('.product-name a').textContent.toLowerCase();
    const productCategory = product.querySelector('.product-category').textContent.toLowerCase();

    if (productName.includes(searchQuery) || productCategory.includes(searchQuery)) {
      product.style.display = 'block';
    } else {
      product.style.display = 'none';
    }
  });
});
