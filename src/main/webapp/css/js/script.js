//script for the menu bar start

menuline = document.querySelector('.menuline');
menuline.onclick = function () {
    nav_bar = document.querySelector('.nav_bar');
    nav_bar.classList.toggle('active');
}

//script for the menu bar stop



//button working of add to cart in home apge starts
document.querySelectorAll('.buy').forEach(function(button) {
  button.addEventListener('click', function() {
    const bottom = button.closest('.wrapper').querySelector('.bottom');
    bottom.classList.add('clicked');
  });
});

document.querySelectorAll('.remove').forEach(function(button) {
  button.addEventListener('click', function() {
    const bottom = button.closest('.wrapper').querySelector('.bottom');
    bottom.classList.remove('clicked');
  });
});

//button working of add to cart in home apge ends





