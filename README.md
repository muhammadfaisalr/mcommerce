# mcommerce-backend

Execute SQL First Before Compiling or Running Spring Boot!

1. Config Datasource Apps in ```application.properties```
2. Running Configuration -> Modify Options -> Check Environment Variables (ALT + E)
3. Java 18 -> Main Class: id.muhammadfaisal.mcommerce.MCommerceApplication
4. Running.


HTTP Request Example : 
> Master Section
1. [POST] Inventory 

```http://localhost:8080/api/v1/inventory```

**Body Request**
```
 {
     "name": "T - Shirt Bagus dan Keren",
     "price": 100000,
     "stock": 100,
     "description": "Baju dengan bahan cotton combed 30s, bagus dan tidak panas. Silahkan di pesan selagi masih ada! :D",
     "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyOpzLtGAIUfXppBQYt50e5Sf2ORDf7FkWdw&usqp=CAU"
 }
```

**Response** : 
```
{
    "id": 152,
    "name": "T - Shirt Bagus dan Keren",
    "price": 100000,
    "stock": 100,
    "description": "Baju dengan bahan cotton combed 30s, bagus dan tidak panas. Silahkan di pesan selagi masih ada! :D",
    "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyOpzLtGAIUfXppBQYt50e5Sf2ORDf7FkWdw&usqp=CAU",
    "createdAt": "2024-01-17T11:18:21.849+00:00",
    "updatedAt": null,
    "deletedAt": null
}
```

2.[GET] Inventories

```http://localhost:8080/api/v1/inventory```

**Response**
```
[
    {
        "id": 152,
        "name": "T - Shirt Bagus dan Keren",
        "price": 100000,
        "stock": 100,
        "description": "Baju dengan bahan cotton combed 30s, bagus dan tidak panas. Silahkan di pesan selagi masih ada! :D",
        "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyOpzLtGAIUfXppBQYt50e5Sf2ORDf7FkWdw&usqp=CAU",
        "createdAt": "2024-01-17T11:18:21.849+00:00",
        "updatedAt": null,
        "deletedAt": null
    },
    {
        "id": 202,
        "name": "Kemeja Polo Polos",
        "price": 150000,
        "stock": 100,
        "description": "Kemeja Polo Polos dengan banyak pilihan warna",
        "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyOpzLtGAIUfXppBQYt50e5Sf2ORDf7FkWdw&usqp=CAU",
        "createdAt": "2024-01-17T11:35:30.951+00:00",
        "updatedAt": null,
        "deletedAt": null
    }
]
```

3. [GET] Order by  Invoice Number

```http://localhost:8080/api/v1/orders/{invoiceNumber}```

**Response**
```
{
    "createdAt": "2024-01-17T11:39:42.949+00:00",
    "updatedAt": "2024-01-17T11:39:43.005+00:00",
    "deletedAt": null,
    "id": 1202,
    "invoiceNumber": "INV-20240117-1202",
    "total": 800000,
    "orderItems": [
        {
            "id": 1002,
            "orderId": 1202,
            "productId": 152,
            "quantity": 2,
            "amount": 100000,
            "name": "T - Shirt Bagus dan Keren",
            "totalAmount": 200000,
            "createdAt": "2024-01-17T11:39:42.961+00:00",
            "updatedAt": null,
            "deletedAt": null
        },
        {
            "id": 1003,
            "orderId": 1202,
            "productId": 202,
            "quantity": 4,
            "amount": 150000,
            "name": "Kemeja Polo Polos",
            "totalAmount": 600000,
            "createdAt": "2024-01-17T11:39:42.999+00:00",
            "updatedAt": null,
            "deletedAt": null
        }
    ]
}
```

4. [POST] Update Stock

```http://localhost:8080/api/v1/inventory/add-stock```
   
**Body Request**
```
 {
    "id": 1,
    "stock": 120
 }
```

**Response**
```
{
    "id": 152,
    "name": "T - Shirt Bagus dan Keren",
    "price": 100000,
    "stock": 108,
    "description": "Baju dengan bahan cotton combed 30s, bagus dan tidak panas. Silahkan di pesan selagi masih ada! :D",
    "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyOpzLtGAIUfXppBQYt50e5Sf2ORDf7FkWdw&usqp=CAU",
    "createdAt": "2024-01-17T11:18:21.849+00:00",
    "updatedAt": "2024-01-17T11:41:33.362+00:00",
    "deletedAt": null
}
```
   
5. [PUT] Update Inventory

```http://localhost:8080/api/v1/inventory```

**Body Request**
```
 {
    "id": 1,
     "name": "Baju",
     "price": 100000,
     "stock": 120,
     "description": "Baju"
 }
```

**Response**
```
{
    "createdAt": "2024-01-17T06:49:26.080+00:00",
    "updatedAt": "2024-01-17T08:19:12.689+00:00",
    "deletedAt": null,
    "id": 1,
    "name": "Baju",
    "price": 100000,
    "stock": 120,
    "description": "Baju"
}
```

6. [GET] Inventory by Id

```http://localhost:8080/api/v1/inventory/{productId}```

**Response**
```
{
    "id": 152,
    "name": "T - Shirt Bagus dan Keren",
    "price": 100000,
    "stock": 100,
    "description": "Baju dengan bahan cotton combed 30s, bagus dan tidak panas. Silahkan di pesan selagi masih ada! :D",
    "image": "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQyOpzLtGAIUfXppBQYt50e5Sf2ORDf7FkWdw&usqp=CAU",
    "createdAt": "2024-01-17T11:18:21.849+00:00",
    "updatedAt": null,
    "deletedAt": null
}
```


> Transcation Section
1. [POST] Place Order

```http://localhost:8080/api/v1/orders```

**Body Request**
```
{
    "orderDetail": [
        {
            "name": "T - Shirt Bagus dan Keren",
            "productId": 152,
            "quantity": 2,
            "amount": 100000
        },
        {
            "name": "Kemeja Polo Polos",
            "productId": 202,
            "quantity": 4,
            "amount": 150000
        }
    ]
}
```

**Response**
```
{
    "message": "Order Placed Successfully",
    "status": "SUCCESS",
    "invoiceNumber": "INV-20240117-1202",
    "orderItems": [
        {
            "name": "T - Shirt Bagus dan Keren",
            "quantity": 2,
            "price": 100000
        },
        {
            "name": "Kemeja Polo Polos",
            "quantity": 4,
            "price": 150000
        }
    ]
}
```

   
Another REST Api available here 
```https://grey-station-944361.postman.co/workspace/RekberApp-Workspace~d6f870ce-31a7-414b-a9db-61d9f5b87659/collection/18935989-f31b44af-6fcf-4241-a4cb-16fd718ca760?action=share&creator=18935989```
