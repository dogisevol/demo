REST API:


Create a tenant
_______________
URL: /tenant
Method: POST
Headers: Content-Type:application/json
Data Param: {
              "name": "Alex",
              "weeklyRentAmount": 430
            }
Response:
            {
              "name": "Alex",
              "weeklyRentAmount": 430,
              "paidToDate": "2016-12-06",
              "credit": 0,
              "id": 3
            }


Get a tenant by id
__________________
URL: /tenant/:id
Method: GET
PATH Param: Required: id=Long
Response:
            {
              "name": "Alex",
              "weeklyRentAmount": 430,
              "paidToDate": "2016-12-06",
              "credit": 0,
              "id": 3
            }

Create a rent receipt
_____________________
URL: /receipt/:tenantId
Method: POST
Headers: Content-Type:application/json
PATH Param: (Required) tenantId=Long
Data Param: {
              "amount": 300
            }
Response:
            {
              "amount": 300,
              "createdDate": "2016-12-06",
              "id": 13
            }

List all rent receipts for a tenant
___________________________________
URL: /receipt/:tenantId
Method: GET
PATH Param: Required: tenantId=Long
Response:
            [
              {
                "amount": 300,
                "createdDate": "2016-12-06",
                "id": 5
              },
              {
                "amount": 300,
                "createdDate": "2016-12-06",
                "id": 6
              },
              {
                "amount": 300,
                "createdDate": "2016-12-06",
                "id": 7
              }
            ]

List all tenants with receipts created in the last 'N' hours
____________________________________________________________
URL: /receipt/updates/:hours
Method: GET
PATH Param: Required: hours=Integer
Response:
        [
          {
            "name": "Mary",
            "weeklyRentAmount": 350,
            "paidToDate": "2016-12-13",
            "credit": 240,
            "id": 1
          },
          {
            "name": "Someone else",
            "weeklyRentAmount": 300,
            "paidToDate": "2016-12-06",
            "credit": 0,
            "id": 2
          }
        ]