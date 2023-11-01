<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./style.css">
    <title>Xác nhận đơn hàng</title>
</head>

<body>

    <table align="center" bgcolor="#dcf0f8" border="0" cellpadding="0" cellspacing="0"
        style="margin:0;padding:0;background-color:#f2f2f2;width:100%!important;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px"
        width="100%">
        <tbody>
            <tr>
                <td align="center"
                    style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal"
                    valign="top">
                    <table border="0" cellpadding="0" cellspacing="0" style="margin-top:15px" width="600">
                        <tbody>
                            <tr style="background:#fff">
                                <td align="left" height="auto" style="padding:15px" width="600">
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                        <tbody>
                                                            <tr>
                                                                <td>
                                                                    <a href="http://localhost:3000" style="display:block;margin-bottom: 20px;"
                                                                        target="_blank">
                                                                        <img alt=""
                                                                            src="https://thumbs.dreamstime.com/b/online-shop-logo-ecommerce-design-vector-187896714.jpg"
                                                                            style="border-radius: 50%;width: 50px; height: 50px;display:block; text-align: center;margin: 0 auto;">
                                                                    </a>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h1
                                                        style="font-size:17px;font-weight:bold;color:#444;padding:0 0 5px 0;margin:0">
                                                        Cảm ơn quý khách ${customer_name} đã đặt hàng tại E-Shop,</h1>
            
                                                    <p
                                                        style="margin:4px 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                                        E-Shop rất vui thông báo đơn hàng #${order_id} của quý khách đã được tiếp nhận
                                                        và
                                                        đang trong quá trình xử lý. E-Shop sẽ thông báo đến quý khách ngay khi hàng
                                                        chuẩn
                                                        bị được giao.</p>
            
                                                    <h3
                                                        style="font-size:13px;font-weight:bold;color:#02acea;text-transform:uppercase;margin:20px 0 0 0;border-bottom:1px solid #ddd">
                                                        Thông tin đơn hàng #${order_id} <span
                                                            style="font-size:12px;color:#777;text-transform:none;font-weight:normal">(${date_order})</span></h3>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td
                                                    style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px">
                                                    <table border="0" cellpadding="0" cellspacing="0" width="100%">
                                                        <thead>
                                                            <tr>
                                                                <th align="left"
                                                                    style="padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold"
                                                                    width="50%">Thông tin thanh toán</th>
                                                                <th align="left"
                                                                    style="padding:6px 9px 0px 9px;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;font-weight:bold"
                                                                    width="50%"> Địa chỉ giao hàng </th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td style="padding:3px 9px 9px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal"
                                                                    valign="top"><span style="text-transform:capitalize">${customer_name}</span><br>
                                                                    <a href="mailto:${customer_email}"
                                                                        target="_blank">${customer_email}</a><br>
                                                                    0382345678
                                                                </td>
                                                                <td style="padding:3px 9px 9px 9px;border-top:0;border-left:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal"
                                                                    valign="top"><span style="text-transform:capitalize">${customer_name}</span><br>
                                                                    <a href="mailto:${customer_email}"
                                                                        target="_blank">${customer_email}</a><br>
                                                                    ${customer_address}<br>
                                                                    T: 0382345678
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2"
                                                                    style="padding:7px 9px 0px 9px;border-top:0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444"
                                                                    valign="top">
                                                                    <p
                                                                        style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                                                        <strong>Phương thức thanh toán: </strong>${ship_type}<br>
                                                                        <strong>Phí vận chuyển: </strong> ${ship_fee}đ<br>
                                                                    </p>
                                                                </td>
                                                            </tr>
                                                        </tbody>
                                                    </table>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <p
                                                        style="margin:4px 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                                        <i>Lưu ý: Đối với đơn hàng đã được thanh toán trước, nhân viên giao nhận có
                                                            thể
                                                            yêu cầu người nhận hàng cung cấp CMND / giấy phép lái xe để chụp ảnh
                                                            hoặc
                                                            ghi lại thông tin.</i>
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <h2
                                                        style="text-align:left;margin:10px 0;border-bottom:1px solid #ddd;padding-bottom:5px;font-size:13px;color:#02acea">
                                                        CHI TIẾT ĐƠN HÀNG</h2>
            
                                                    <table border="0" cellpadding="0" cellspacing="0" style="background:#f5f5f5"
                                                        width="100%">
                                                        <thead>
                                                            <tr>
                                                                <th align="left" bgcolor="#02acea"
                                                                    style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">
                                                                    Sản phẩm</th>
                                                                <th align="left" bgcolor="#02acea"
                                                                    style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">
                                                                    Đơn giá</th>
                                                                <th align="left" bgcolor="#02acea"
                                                                    style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">
                                                                    Số lượng</th>
                                                                <th align="left" bgcolor="#02acea"
                                                                    style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">
                                                                    Giảm giá</th>
                                                                <th align="right" bgcolor="#02acea"
                                                                    style="padding:6px 9px;color:#fff;font-family:Arial,Helvetica,sans-serif;font-size:12px;line-height:14px">
                                                                    Tổng tạm</th>
                                                            </tr>
                                                        </thead>
                                                        <tbody bgcolor="#eee"
                                                            style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px">
                                                            <#list orders as order>
															 	<tr>
	                                                                <td align="left" style="padding:3px 9px" valign="top"><span>${order.product_name}</span><br>
	                                                                </td>
	                                                                <td align="left" style="padding:3px 9px" valign="top">
	                                                                    <span>${order.price}đ</span>
	                                                                </td>
	                                                                <td align="left" style="padding:3px 9px" valign="top">${order.quantity}</td>
	                                                                <td align="left" style="padding:3px 9px" valign="top">
	                                                                    <span>0đ</span>
	                                                                </td>
	                                                                <td align="right" style="padding:3px 9px" valign="top">
	                                                                    <span>${order.total_price}đ</span>
	                                                                </td>
                                                            	</tr>
															</#list>
                                                        </tbody>
                                                        <tfoot
                                                            style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px">
                                                            <tr>
                                                                <td align="right" colspan="4" style="padding:5px 9px">Tạm tính</td>
                                                                <td align="right" style="padding:5px 9px"><span>${total_noship}đ</span></td>
                                                            </tr>
                                                            <tr>
                                                                <td align="right" colspan="4" style="padding:5px 9px">Phí vận chuyển
                                                                </td>
                                                                <td align="right" style="padding:5px 9px"><span>${ship_fee}đ</span></td>
                                                            </tr>
                                                            <tr bgcolor="#eee">
                                                                <td align="right" colspan="4" style="padding:7px 9px">
                                                                    <strong><big>Tổng
                                                                            giá trị đơn hàng</big> </strong></td>
                                                                <td align="right" style="padding:7px 9px">
                                                                    <strong><big><span>${total}đ</span> </big> </strong>
                                                                </td>
                                                            </tr>
                                                        </tfoot>
                                                    </table>
            
                                                    <div style="margin:auto">
                                                        <a href="http://localhost:3000/customer/order/history/detail/${order_id}"
                                                            style="display:inline-block;text-decoration:none;background-color:#00b7f1!important;margin-right:30px;text-align:center;border-radius:3px;color:#fff;padding:5px 10px;font-size:12px;font-weight:bold;margin-left:35%;margin-top:10px"
                                                            target="_blank">Chi
                                                            tiết đơn hàng
                                                        </a>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;
                                                    <p
                                                        style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal;border:1px #14ade5 dashed;padding:5px;list-style-type:none">
                                                        Từ ngày 14/2/2015, E-Shop sẽ không gởi tin nhắn SMS khi đơn hàng của bạn
                                                        được xác
                                                        nhận thành công. Chúng tôi chỉ liên hệ trong trường hợp đơn hàng có thể bị
                                                        trễ
                                                        hoặc không liên hệ giao hàng được.
                                                    </p>
            
                                                    <p
                                                        style="margin:10px 0 0 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal">
                                                        Mọi thắc mắc và góp ý, quý khách vui lòng liên hệ với chúng tôi qua SĐT
                                                        <span style="color: rgb(2, 172, 234);">0123456789</span>.
                                                        Đội ngũ của chúng tôi luôn sẵn sàng hỗ trợ bạn.
                                                    </p>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;
                                                    <p
                                                        style="font-family:Arial,Helvetica,sans-serif;font-size:12px;margin:0;padding:0;line-height:18px;color:#444;font-weight:bold">
                                                        Một lần nữa E-Shop cảm ơn quý khách.
                                                    </p>
                                                    <p
                                                        style="margin:10px 0 0 0;font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;padding:4px;color:orange;font-style:italic;border:1px dashed #4daae0">
                                                        Đơn hàng được vận chuyển đến hoặc qua các khu vực đang bị ảnh hưởng bởi
                                                        Covid-19
                                                        sẽ có thể giao chậm hơn dự kiến. Kính mong quý khách thông cảm.
                                                    </p>
                                                    <p style="font-family:Arial,Helvetica,sans-serif;font-size:12px;color:#444;line-height:18px;font-weight:normal;text-align:right">
                                                        <strong><a href="http://localhost:3000" style="color:#00a3dd;text-decoration:none;font-size:14px" target="_blank">E-Shop</a>
                                                        </strong></p>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </td>
            </tr>
        </tbody>
    </table>

</body>

</html>