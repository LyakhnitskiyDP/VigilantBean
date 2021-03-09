<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">

    <spring:url value="/resources/styles/rootStyle.css" var="rootStyle" />
    <spring:url value="/resources/styles/mainStyle.css" var="mainStyle" />
    <spring:url value="/resources/styles/accountStyle.css" var="accountStyle" />
    <spring:url value="/resources/styles/includesStyles/headerStyle.css" var="headerStyle" />
    <spring:url value="/resources/styles/includesStyles/footerStyle.css" var="footerStyle" />


    <title>Admit page</title>

    <link rel="stylesheet" href="${rootStyle}" />
    <link rel="stylesheet" href="${mainStyle}" />
    <link rel="stylesheet" href="${accountStyle}" />
    <link rel="stylesheet" href="${headerStyle}" />
    <link rel="stylesheet" href="${footerStyle}" />

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <spring:url value="/resources/scripts/tabsScript.js" var="tabsScript"/>
    <script type="text/javascript" src="${tabsScript}"></script>

    <spring:url value="/resources/scripts/adminPageScript.js" var="adminPageScript"/>
    <script type="text/javascript" src="${adminPageScript}"></script>

</head>
<body>

<div class="wrapper">

  <c:import url="/WEB-INF/views/includes/header.jsp" />

  <main>

  <div id="tabs">

    <div class="tab" id="myAccountTab">
        <span class="tab-label"><spring:message code="view.admin.account.label" /></span>
    </div>

    <div class="tab" id="addProductTab" onClick="refreshCategories(event)">
        <span class="tab-label"><spring:message code="view.admin.addProduct.label" /></span>
    </div>

    <div class="tab" id="editProductTab">
        <span class="tab-label"><spring:message code="view.admin.editProduct.label" /></span>
    </div>

    <div class="tab" id="addCategoryTab">
        <span class="tab-label"><spring:message code="view.admin.addCategory.label" /></span>
    </div>

    <div class="tab" id="editCategoryTab">
        <span class="tab-label"><spring:message code="view.admin.editCategory.label" /></span>
    </div>

    <div class="tab" id="applyDiscountTab">
        <span class="tab-label"><spring:message code="view.admin.applyDiscount.label" /></span>
    </div>

    <div class="tab" id="logoutTab">
        <span class="tab-label"><spring:message code="view.account.logout.label" /></span>
    </div>

  </div>


  <div id="tab-content">

    <div id="myAccountTab-content">
        <h2><spring:message code="view.admin.account.label" /></h2>

        <div id="user-data">
            <div class="field">
                <span class="key"><spring:message code="view.account.myAccount.username.label" />: </span>
                <span class="value">${user.username}</span>
            </div>

            <div class="field">
                <span class="key"><spring:message code="view.account.myAccount.email.label" />: </span>
                <span class="value">${userEmail}</span>
            </div>

            <div class="field">
                <span class="key"><spring:message code="view.account.myAccount.registrationDate.label" />: </span>
                <span class="value">${user.registrationDate}</span>
            </div>
        </div>
    </div>

    <div id="addProductTab-content">
        <h2><spring:message code="view.admin.addProduct.label" /></h2>
        <p><spring:message code="view.admin.addProduct.description" /></p>

        <form action="admin/addProduct"
              method="POST"
              enctype="multipart/form-data"
              class="ajaxForm">

            <div class="error-pane"
                 id="newProductErrorPane"></div>

            <fieldset>

                <div class="input-group" >
                    <label class="form-label" for="product-name">
                        Product name
                    </label>

                    <div class="form-input-field">

                        <input id="product-name" class="field-input" name="newProductName"
                               type="text" autocomplete="off"/>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-description">
                        Product description
                    </label>

                    <div class="form-input-field">

                        <textarea id="product-description" name="newProductDescription"
                                autocomplete="off"></textarea>
                    </div>
                </div>

                <div class="input-group" id="category-selector">

                    <label class="form-label" >
                        Categories
                    </label>

                    <div class="form-input-field" id="categories-input-field">

                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-quantityPerUnit">
                        Quantity per unit
                    </label>

                    <div class="form-input-field">

                        <input id="product-quantityPerUnit" class="field-input" name="newProductQuantityPerUnit"
                               type="number" min="1" autocomplete="off"/>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-quantityPerUnit">
                       Units in stock
                    </label>

                    <div class="form-input-field">

                        <input id="product-quantityPerUnit" class="field-input" name="newProductUnitsInStock"
                               type="number" min="1" autocomplete="off"/>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-unitWeight">
                       Unit weight in grams
                    </label>

                    <div class="form-input-field">

                        <input id="product-unitWeight" class="field-input" name="newProductUnitWeight"
                               type="number" min="1"  autocomplete="off"/>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-unitPrice">
                      Unit price in US dollars
                    </label>

                    <div class="form-input-field">

                        <input id="product-unitPrice" class="field-input" name="newProductUnitPrice"
                               type="number" min="0" autocomplete="off"/>
                    </div>
                </div>



                 <div class="input-group" >
                    <label class="form-label" for="product-origins">
                       Origins
                    </label>

                    <div class="form-input-field">

                        <textarea id="product-origins" name="newProductOrigins"
                                autocomplete="off"></textarea>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-manufacturer">
                        Product manufacturer
                    </label>

                    <div class="form-input-field">

                        <input type="text" id="product-manufacturer" class="field-input"
                               name="newProductManufacturer"
                               autocomplete="off">
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-ingredients">
                        Product ingredients
                    </label>

                    <div class="form-input-field">

                        <textarea id="product-ingredients" name="newProductIngredients"
                                autocomplete="off"></textarea>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="product-allergyInformation">
                        Product allergy information
                    </label>

                    <div class="form-input-field">

                        <textarea id="product-description" name="newProductAllergyInformation"
                                autocomplete="off"></textarea>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="newProductMainPhoto">
                        Main Photo
                    </label>

                    <div class="form-input-field">

                        <input name="newProductMainPhoto" class="file-input"
                               id="newProductMainPhoto"
                               type="file" onchange="previewProductMainImage();" />

                        <button class="file-upload-button"
                                id="product-main-photo-upload-button">
                                Choose main photo
                        </button>
                    </div>
                </div>

                <div class="photo-preview">

                    <img id="newProduct-mainPhotoPreview">

                </div>

                 <div class="input-group" >
                     <label class="form-label" for="newProductSecondaryPhotos">
                         Secondary Photos (optional)
                     </label>

                     <div class="form-input-field">

                         <input name="newProductSecondaryPhotos" class="file-input"
                                id="newProductSecondaryPhotos"
                                onchange="previewProductSecondaryImages(event);"
                                type="file" multiple/>

                         <button
                                 onClick="selectProductSecondaryImages(event);"
                                 id="product-secondary-photos-upload-button">
                                 Choose secondary photos
                         </button>
                     </div>
                 </div>

                 <div class="photo-preview" id="secondaryPicturesPreview">


                 </div>

                <div class="input-group" >

                    <input type="submit" value="Add" />

                </div>

            </fieldset>

        </form>

    </div>

    <div id="editProductTab-content">
        <p>TODO: implement it</p>
    </div>

    <%-- Add new Category --%>
    <div id="addCategoryTab-content">
        <h2>Add new Category</h2>

        <form action="admin/addCategory"
              method="POST"
              enctype="multipart/form-data"
              class="ajaxForm">

            <div class="error-pane"
                 id="newCategoryErrorPane"></div>

            <fieldset>

                <div class="input-group" >
                    <label class="form-label" for="category-name">
                        Category name
                    </label>

                    <div class="form-input-field">

                        <input id="category-name" class="field-input" name="newCategoryName"
                               type="text" autocomplete="off"/>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="category-description">
                        Category description
                    </label>

                    <div class="form-input-field">

                        <textarea id="category-description" name="newCategoryDescription"
                                autocomplete="off"></textarea>
                    </div>
                </div>

                <div class="input-group" >
                    <label class="form-label" for="newCategoryPhoto">
                        Photo
                    </label>

                    <div class="form-input-field">

                        <input name="categoryPhoto" class="file-input" id="newCategoryPhoto"
                                type="file" onchange="previewCategoryImage();"/>

                        <button class="file-upload-button"
                                id="category-photo-upload-button">
                                Choose category photo
                        </button>
                    </div>
                </div>

                <div class="photo-preview">

                    <img id="newCategory-photoPreview">

                </div>

                <div class="input-group" >

                    <input type="submit" value="Add" />

                </div>

            </fieldset>

        </form>

    </div>

    <div id="editCategoryTab-content">
        <p>Impl</p>
    </div>

    <div id="applyDiscountTab-content">
        <p>TODO: implement it</p>
    </div>

    <div id="logoutTab-content">
        <h2><spring:message code="view.account.logout.label" /></h2>

        <p><spring:message code="view.account.logout.description" /></p>

        <spring:url value="/logout" var="logoutURL"/>
        <form action="${logoutURL}">
            <input type="submit" value='<spring:message code="view.yes"/>'/>
        </form>
    </div>
  </div>

  </main>

</div>

<c:import url='/WEB-INF/views/includes/footer.jsp' />

</body>
</html>