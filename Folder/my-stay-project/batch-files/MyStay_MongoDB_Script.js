// Note: Please don't run this script beacause all the collection are already created in the following DBs. 
//If you are using other DBs then change the URL, UserName and Password of all Databases.

//Login Service
//Connect to Orchard4 DB
var db = connect('orchardmongo.eastus.cloudapp.azure.com:27017/Orchard4', "mongoUser4", "User4pwd")
//Create a collection for Login Service
db.createCollection("user", { capped : true, size : 2800000, max : 10000 })


//Booking Service
//Connect to Orchard9 DB
var db1 = connect('orchardmongo.eastus.cloudapp.azure.com:27017/Orchard9', "mongoUser9", "User9pwd")
//Create a collection for Booking Service
db1.createCollection("BookRoom", { capped : true, size : 2800000, max : 10000 })

//Catalog Service
//Connect to Orchard7 DB
var db2 = connect('orchardmongo.eastus.cloudapp.azure.com:27017/Orchard7', "mongoUser7", "User7pwd")
//Create a collection for Catalog Service
db2.createCollection("Hotels", { capped : true, size : 2800000, max : 10000 })

//Insert data in hotels collection
db2.Hotels.insertMany(
		[
		{
			"_id" : "1001",
			"hotelName" : "Pearless Hotel",
			"address" : {
				"street" : "Haldiram",
				"city" : "Kolkata",
				"state" : "West Bengal",
				"pin" : 721066
			},
			"distance" : 5,
			"offers" : [ 
				"5% Discount for UPI Payment", 
				"5% Discount for SBI Credit Card"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 4500,
					"services" : [ 
						"wifi"
					]
				}, 
				{
					"roomNo" : "103",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 700,
					"services" : [ 
						"wifi", 
						"ac"
					]
				}, 
				{
					"roomNo" : "102",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 1400,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast"
					]
				}
			]
		},
		{
			"_id" : "1002",
			"hotelName" : "Marriyot",
			"address" : {
				"street" : "Em bypass",
				"city" : "Kolkata",
				"state" : "West Bengal",
				"pin" : 700066
			},
			"distance" : 5,
			"offers" : [ 
				"5% Discount for SBI Credit Card", 
				"5% Discount for AmazonPay", 
				"2% Discount for Paytm"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "budget",
					"availability" : "yes",
					"price" : 700,
					"services" : [ 
						"wifi", 
						"ac"
					]
				}, 
				{
					"roomNo" : "102",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 1200,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast", 
						"TV"
					]
				}, 
				{
					"roomNo" : "103",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 900,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast"
					]
				}
			]
		},
		{
			"hotelId" : "1003",
			"hotelName" : "Sri Balaji Hotel",
			"address" : {
				"street" : "Marathahalli",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560066
			},
			"distance" : 3,
			"offers" : [ 
				"5% Discount for UPI Payment", 
				"5% Discount for SBI Credit Card", 
				"5% Discount for AmazonPay"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "budget",
					"availability" : "No",
					"price" : 500,
					"services" : [ 
						"wifi"
					]
				}, 
				{
					"roomNo" : "102",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 900,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast"
					]
				}, 
				{
					"roomNo" : "103",
					"roomType" : "semi-luxury",
					"availability" : "No",
					"price" : 700,
					"services" : [ 
						"wifi", 
						"ac"
					]
				}
			]
		},
		{
			"hotelId" : "1004",
			"hotelName" : "Marriyot ",
			"address" : {
				"street" : "Whitefield",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560066
			},
			"distance" : 8,
			"offers" : [ 
				"5% Discount for UPI Payment", 
				"5% Discount for SBI Credit Card", 
				"5% Discount for AmazonPay", 
				"2% Discount for Paytm"
			],
			"rooms" : [ 
				{
					"roomNo" : "101.0",
					"roomType" : "budget",
					"availability" : "yes",
					"price" : 700,
					"services" : [ 
						"wifi", 
						"ac"
					]
				}, 
				{
					"roomNo" : "102.0",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 1500,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast", 
						"TV"
					]
				}, 
				{
					"roomNo" : "103.0",
					"roomType" : "semi-luxury",
					"availability" : "no",
					"price" : 900,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast"
					]
				}
			]
		},
		{
			"_id" : "12",
			"hotelName" : "ABC Hotel",
			"address" : {
				"street" : "K. Gate",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560037
			},
			"distance" : 5,
			"offers" : [ 
				"upto 15% cashback on HDFC card offer", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay", 
				"10% OFF on UPI Payment", 
				"Upto Rs.500 cashback on LazyPay"
			],
			"rooms" : [ 
				{
					"roomNo" : "305",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 2500,
					"services" : [ 
						"wi-fi"
					]
				}, 
				{
					"roomNo" : "306",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 5000,
					"services" : [ 
						"breakfast", 
						"wi-fi"
					]
				}
			]
		},

		/* 6 */
		{
			"_id" : "1003",
			"hotelName" : "Homestay",
			"address" : {
				"street" : "Stalin cross road",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560043
			},
			"distance" : 2,
			"offers" : [
				"Flat Rs.70 OFF using LazyPay"
			],
			"rooms" : [ 
				{
					"roomNo" : "102",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 5500,
					"services" : [ 
						"wi-fi"
					],
					"url" : ""
				}, 
				{
					"roomNo" : "110",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 2500,
					"services" : [ 
						"wifi", 
						"ac"
					]
				}
			]
		},

		/* 7 */
		{
			"_id" : "1006",
			"hotelName" : "Fluffy Stay",
			"address" : {
				"street" : "Jayanagar",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560037
			},
			"distance" : 3,
			"offers" : [ 
				"Flat Rs.100 OFF using LazyPay"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 4500,
					"services" : [ 
						"wi-fi"
					]
				}
			]
		},
		{
			"_id" : "1010",
			"hotelName" : "Hotel Tranquil",
			"address" : {
				"street" : "JP Nagar",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560037
			},
			"distance" : 3,
			"offers" : [ 
				"Flat Rs.100 OFF using LazyPay"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 4500,
					"services" : [ 
						"wi-fi"
					]
				}
			]
		},
		{
			"_id" : "1011",
			"hotelName" : "Stay ByHill",
			"address" : {
				"street" : "JP Nagar",
				"city" : "Bangalore",
				"state" : "Karnataka",
				"pin" : 560037
			},
			"distance" : 3,
			"offers" : [ 
				"Flat Rs.100 OFF using LazyPay"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 4500,
					"services" : [ 
						"wi-fi"
					]
				}
			]
		},
		{
			"_id" : "1021",
			"hotelName" : "Laxmi Hotel",
			"address" : {
				"street" : "Haldiram",
				"city" : "Kolkata",
				"state" : "West Bengal",
				"pin" : 721066
			},
			"distance" : 5,
			"offers" : [ 
				"Flat 5% Discount for UPI Payment", 
				"Upto 5% Discount for OBC Credit Card"
			],
			"rooms" : [ 
				{
					"roomNo" : "101",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 4500,
					"services" : [ 
						"wifi"
					]
				}, 
				{
					"roomNo" : "103",
					"roomType" : "semi-luxury",
					"availability" : "yes",
					"price" : 700,
					"services" : [ 
						"wifi", 
						"ac"
					]
				}, 
				{
					"roomNo" : "102",
					"roomType" : "luxury",
					"availability" : "yes",
					"price" : 1400,
					"services" : [ 
						"wifi", 
						"ac", 
						"breakfast"
					]
				}
			]
		}
	]
)