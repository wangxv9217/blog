document.addEventListener('DOMContentLoaded', function() {
  var p2Elements = document.querySelectorAll('.card .text #p2'); // 获取所有卡片中的p2元素

  for (var i = 0; i < p2Elements.length; i++) {
    var p2 = p2Elements[i];
    var maxCharacters = 100; // 设置最大字数

    if (p2.textContent.length > maxCharacters) {
      var truncatedText = p2.textContent.slice(0, maxCharacters) + '...';
      p2.textContent = truncatedText;
    }
  }
});
