window.addEventListener('DOMContentLoaded', function() {
  var quotes = [
    "  (～￣▽￣)～",
    "╰(*°▽°*)╯",
    "Σ(っ °Д °;)っ",
	"♪(′∇`*)",
  ];

  var randomIndex = Math.floor(Math.random() * quotes.length);
  var randomQuote = quotes[randomIndex];

  var quoteElement = document.getElementById('random-quote');
  quoteElement.textContent = randomQuote;
});

window.addEventListener('DOMContentLoaded', function() {
      var inputElement = document.getElementById('password');
      var pElement = document.getElementById('random-quote');

      inputElement.addEventListener('focus', function() {
        pElement.textContent = '(/ω＼)';
      });

      inputElement.addEventListener('blur', function() {
        pElement.textContent = '╰(*°▽°*)╯';
      });
    });