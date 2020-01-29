start "Mystay-Eureka-Naming Server" java -jar  C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/eureka-naming-server/target/eureka-naming-server-0.0.1-SNAPSHOT.jar
timeout /t 10
start "Mystay-Config-Server" java -jar   C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-config-server/target/mystay-config-server-0.0.1-SNAPSHOT.jar
timeout /t 10
start "Mystay-Zuul-Gateway" java -jar      C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-zuul-gateway/target/mystay-zuul-gateway-0.0.1-SNAPSHOT.jar
start "Mystay-Admin-Service" java -jar C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-admin-service/target/mystay-admin-service-0.0.1-SNAPSHOT.jar
start "Mystay-Search-Service" java -jar  C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-search-service/target/mystay-search-service-0.0.1-SNAPSHOT.jar
start "Mystay-Catalog-Service" java -jar  C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-catalog-service/target/mystay-catalog-service-0.0.1-SNAPSHOT.jar
start "Mystay-Booking-Service" java -jar  C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-booking-service/target/mystay-booking-service-0.0.1-SNAPSHOT.jar
start "Mystay-Payment-Service" java -jar  C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-payment-service/target/mystay-payment-service-0.0.1-SNAPSHOT.jar
start "Mystay-Turbine-Dashboard" java -jar  C:/Users/M1052242/Downloads/book-mystay-workspace/my-stay-project/mystay-turbine-dashbord/target/mystay-turbine-dashbord-0.0.1-SNAPSHOT.jar
