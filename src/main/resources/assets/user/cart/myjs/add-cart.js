function renderCartItem(item) {
    return `
            <div class="flex-shrink-0">
                <img src="${item.images.fileUrl}"
                     class="img-fluid" style="width: 150px;" alt="Generic placeholder image">
            </div>
            <div class="flex-grow-1 ms-3">
                <a href="#!" class="float-end text-black"><i class="fas fa-times"></i></a>
                <h5 class="text-primary">${item.foodName}</h5>
                <h6 style="color: #9e9e9e;">Color: white</h6>
                <div class="d-flex align-items-center">
                    <p class="fw-bold mb-0 me-5 pe-3">${item.price}</p>
                    <div class="def-number-input number-input safari_only">
                        <button class="minus" data-id="${item.foodId}">-</button>
                        <input class="quantity fw-bold text-black" min="0" name="quantity" value="${item.quantity}"
                               data-id="${item.foodId}" type="number">
                        <button class="plus" data-id="${item.foodId}">+</button>
                    </div>
                </div>
            </div>
    `
}

getAllCartItems = () => {
    return $.ajax({
        type: 'GET',
        url: "http://localhost:8080/api/carts"
    })
        .done((data) => {
            console.log(data)
        }).fail((error) => {
            console.log(error)
        })
}

handleShowCart = () => {
    $("#btnShowCart").on('click', () => {
        getAllCartItems().then((data) => {
            let items = data.cartDetailList;
            console.log("hi")
            $('#cart-view-line').empty();

            $.each(items, (index, item) => {
                let str = renderCartItem(item);
                $('#cart-view-line').append(str);
            })

            $('#total-view-cart').text(data.totalAmount + ' đ');

            handleAddQuantity();

            handleMinusQuantity();

            handleChangeQuantity();
        });
    })
}

handleAddQuantity = () => {
    $('.cart-add-quantity').on('click', function () {
        let productId = $(this).data('id');
        let obj = {
            productId,
            quantity: 1
        }

        page.commands.addCartItem(obj)
            .then((data) => {
                let items = data.items;

                $('#cart-view-line').empty();

                $.each(items, (index, item) => {
                    let str = AppBase.renderCartItem(item);
                    $('#cart-view-line').append(str);
                })

                $('#total-view-cart').text(data.totalAmount + ' đ');

                page.commands.handleAddQuantity();

                page.commands.handleMinusQuantity();

                page.commands.handleChangeQuantity();

                AppBase.IziToast.showSuccessAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thành công')
            })
            .catch(() => {
                AppBase.IziToast.showErrorAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thất bại');
            });
    })
}

handleMinusQuantity = () => {
    $('.cart-minus-quantity').on('click', function () {
        let productId = $(this).data('id');
        let obj = {
            productId,
            quantity: 1
        }

        page.commands.minusCartItem(obj)
            .then((data) => {
                let items = data.items;

                $('#cart-view-line').empty();

                $.each(items, (index, item) => {
                    let str = AppBase.renderCartItem(item);
                    $('#cart-view-line').append(str);
                })

                $('#total-view-cart').text(data.totalAmount + ' đ');

                page.commands.handleAddQuantity();

                page.commands.handleMinusQuantity();

                page.commands.handleChangeQuantity();

                AppBase.IziToast.showSuccessAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thành công')
            })
            .catch(() => {
                AppBase.IziToast.showErrorAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thất bại');
            });
    })
}

handleChangeQuantity = () => {
    $('.cart-quantity').on('change', function (e) {
        let quantity = +e.target.value;

        let productId = $(this).data('id');

        let obj = {
            productId,
            quantity: 0
        }

        if (quantity <= 0 || isNaN(quantity)) {
            AppBase.showDeleteCartItemConfirmDialog().then((result) => {
                if (result.isConfirmed) {
                    page.commands.deleteCartItem(obj)
                        .then((data) => {
                            let items = data.items;

                            $('#cart-view-line').empty();

                            $.each(items, (index, item) => {
                                let str = AppBase.renderCartItem(item);
                                $('#cart-view-line').append(str);
                            })

                            $('#total-view-cart').text(data.totalAmount + ' đ');

                            page.commands.handleAddQuantity();

                            page.commands.handleMinusQuantity();

                            page.commands.handleChangeQuantity();

                            AppBase.IziToast.showSuccessAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thành công')
                        })
                        .catch(() => {
                            AppBase.IziToast.showErrorAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thất bại');
                        });
                }
            });
        }
        else {
            page.commands.changeQuantityCartItem(obj)
                .then((data) => {
                    let items = data.items;

                    $('#cart-view-line').empty();

                    $.each(items, (index, item) => {
                        let str = AppBase.renderCartItem(item);
                        $('#cart-view-line').append(str);
                    })

                    $('#total-view-cart').text(data.totalAmount + ' đ');

                    page.commands.handleAddQuantity();

                    page.commands.handleMinusQuantity();

                    page.commands.handleChangeQuantity();

                    AppBase.IziToast.showSuccessAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thành công')
                })
                .catch(() => {
                    AppBase.IziToast.showErrorAlert('Cập nhật số lượng sản phẩm trong giỏ hàng thất bại');
                });
        }


    })
}

handleAddCart = () => {
    $('.add-cart').on('click', function () {
        let productId = $(this).data("id");

        let obj = {
            productId,
            quantity: 1
        }

        // page.commands.addCartItem(obj)
        //     .then(() => {
        //         AppBase.IziToast.showSuccessAlert('Thêm sản phẩm vào giỏ hàng thành công')
        //     })
        //     .catch(() => {
        //         AppBase.IziToast.showErrorAlert('Thêm sản phẩm vào giỏ hàng thất bại');
        //     });

    })
}

window.onload = async () => {
    await getAllCartItems();


}

