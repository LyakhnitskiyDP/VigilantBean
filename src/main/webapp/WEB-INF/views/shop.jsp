<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/shopPageStyle.css" var="shopPageStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />

    <title>Vigilant Bean</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />
    <link rel="stylesheet" href="${shopPageStyle}" />
</head>
<body>

<div class="wrapper">

  <c:import url="includes/header.jsp" />


  <spring:url value="/resources/images/categories/${categoryImage}" var="categoryImageURL" />
  <section id="header-showcase"
           style="background-image: url('${categoryImageURL}');">

      <h1>${categoryName}</h1>

  </section>

  <main class="content">


    <div id="product-area">
      <label id="sort-by-label">Sort by
        <select name="sort_by_attribute">
          <option value="newest">Newest</option>
          <option value="chipest">Chipest</option>
          <option value="biggest_discount">Biggest discount</option>
        </select>
      </label>

      <div class="product-showcase">

      <c:forEach items="${products}" var="product">

        <spring:url value="/shop/product?productId=${product.productId}" var="productURL" />
        <div class="product-item" onclick="location.href = '${productURL}'">

          <spring:url value="/resources/images/products/${product.mainPicture.name}.${product.mainPicture.extension}" var="productImageURL" />
          <img src="${productImageURL}" alt="product" />
          <p class="product-name">${product.name}</p>
          <p class="product-description">${product.description}</p>
        </div>
      </c:forEach>

      </div>


    </div>

    <div id="categories-area">
      <p>
        <spring:message code="view.shop.categories.label" />
      </p>

      <ul>
        <spring:url value="/resources/images/icons/coffee-bean.svg" var="coffeeBeanIcon" />

        <li>
          <nobr>
          <img src="${coffeeBeanIcon}" alt="coffee-bean" class="icon" />

          <spring:url value="/shop" var="categoryURL" />

          <a href="${categoryURL}" class="category-label">
          All products
          </a>
          </nobr>
        </li>

        <c:forEach items="${categories}" var="category">
        <li>
          <nobr>
          <img src="${coffeeBeanIcon}" alt="coffee-bean" class="icon">
          <spring:url value="/shop/${category.shortName}" var="categoryURL" />
          <a href="${categoryURL}" class="category-label">
          ${category.name}
          </a>
          </nobr>
        </li>
        </c:forEach>

      </ul>
    </div>



  </main>

  <div id="pages-list">


    <c:forEach items="${pageList}" var="pageNumber">
      <a href="?page=${pageNumber}">
      <div class="page-number-label">
        ${pageNumber}
      </div>
      </a>
    </c:forEach>
  </div>

  </div>

  <c:import url='includes/footer.jsp' />

</body>
</html>