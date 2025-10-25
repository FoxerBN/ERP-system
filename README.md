# 📦 ERP Stock Management System

> A modern, feature-rich ERP system for warehouse management built with JavaFX and MySQL

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![JavaFX](https://img.shields.io/badge/JavaFX-21-blue.svg)](https://openjfx.io/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)](https://www.mysql.com/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

## ✨ Features

- 📊 **Product Management** - Add, edit, delete, and track products with pricing and stock levels
- 📥 **Stock In (Purchases)** - Record incoming inventory from suppliers
- 📤 **Stock Out (Sales)** - Manage outgoing inventory to customers
- 👥 **Supplier Management** - Track supplier information and purchase history
- 🛒 **Customer Management** - Maintain customer records and sales history
- 📄 **PDF Export** - Generate professional PDF reports for transactions
- 🎨 **Modern UI** - Clean, intuitive interface built with JavaFX

## 🏗️ Project Structure

```
ERP-system/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── sk/foxer/erpstock/
│   │   │       ├── config/              # Database configuration
│   │   │       │   └── DatabaseConfig.java
│   │   │       ├── controller/          # UI Controllers
│   │   │       │   ├── MainController.java
│   │   │       │   ├── stock/           # Product management controllers
│   │   │       │   ├── stockin/         # Purchase controllers
│   │   │       │   └── stockout/        # Sales controllers
│   │   │       ├── dao/                 # Data Access Objects
│   │   │       │   ├── stock/           # Product DAO
│   │   │       │   ├── stockin/         # Stock In & Supplier DAO
│   │   │       │   └── stockout/        # Stock Out & Customer DAO
│   │   │       ├── mapper/              # ResultSet to Object mappers
│   │   │       │   ├── stock/
│   │   │       │   ├── stockin/
│   │   │       │   └── stockout/
│   │   │       ├── model/               # Domain models
│   │   │       │   ├── stock/           # Product model
│   │   │       │   ├── stockin/         # StockIn & Supplier models
│   │   │       │   └── stockout/        # StockOut & Customer models
│   │   │       ├── util/                # Utility classes
│   │   │       │   ├── HandleExportUtil.java
│   │   │       │   └── UIHelper.java
│   │   │       └── ERPApplication.java  # Main application entry
│   │   └── resources/
│   │       └── sk/foxer/erpstock/
│   │           ├── view/                # FXML view files
│   │           │   ├── main.fxml
│   │           │   ├── stock/
│   │           │   ├── stockin/
│   │           │   └── stockout/
│   │           └── style/               # CSS stylesheets
│   └── test/
├── pom.xml                              # Maven dependencies
└── README.md
```

## 🚀 Getting Started

### Prerequisites

- ☕ **Java 21** or higher
- 🗄️ **MySQL 8.0** or higher
- 📦 **Maven 3.6+**

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/FoxerBN/ERP-system.git
   cd ERP-system
   ```

2. **Set up MySQL Database**
   ```sql
   CREATE DATABASE erp_stock;
   ```
   
   Create the required tables:
   - `products` (id, code, name, unit, purchase_price, sale_price, current_stock)
   - `suppliers` (id, name, address, contact)
   - `customers` (id, name, address, contact)
   - `stock_in` (id, date, supplier_id, product_id, quantity, unit_price, total)
   - `stock_out` (id, date, customer_id, product_id, quantity, unit_price)

3. **Configure Database Connection**
   
   Create `src/main/resources/application.properties`:
   ```properties
   db.url=jdbc:mysql://localhost:3306/erp_stock
   db.user=your_username
   db.password=your_password
   ```

4. **Build and Run**
   ```bash
   mvn clean install
   mvn javafx:run
   ```

## 🎯 How It Works

### Architecture Overview

```
┌─────────────┐
│   JavaFX    │  ← User Interface Layer
│  Controllers│
└──────┬──────┘
       │
┌──────▼──────┐
│    DAO      │  ← Data Access Layer
│  (Database) │
└──────┬──────┘
       │
┌──────▼──────┐
│   MySQL     │  ← Persistence Layer
│  Database   │
└─────────────┘
```

### Core Components

- **📱 Controllers**: Handle UI logic and user interactions
- **🗃️ DAO (Data Access Objects)**: Manage database operations (CRUD)
- **🔄 Mappers**: Convert database ResultSets to Java objects
- **📦 Models**: Represent business entities (Product, Supplier, Customer, etc.)
- **⚙️ Config**: Database connection management with connection pooling

### Key Features Workflow

1. **Product Management** 📦
   - View all products in a table
   - Add/Edit products with validation
   - Delete products with confirmation
   - Track current stock levels

2. **Stock In (Purchases)** 📥
   - Record purchases from suppliers
   - Automatically updates product stock
   - View purchase history
   - Filter by supplier

3. **Stock Out (Sales)** 📤
   - Process customer orders
   - Deduct from product stock
   - Generate sales records
   - View customer purchase history

4. **PDF Export** 📄
   - Export transaction reports
   - Professional formatting
   - Uses Apache PDFBox library

## 🛠️ Technologies Used

| Technology | Purpose |
|------------|---------|
| **JavaFX 21** | Desktop UI framework |
| **MySQL 8.0** | Relational database |
| **Maven** | Build & dependency management |
| **Apache PDFBox** | PDF generation |
| **ControlsFX** | Enhanced UI controls |
| **ValidatorFX** | Form validation |
| **Ikonli** | Icon pack integration |


## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 👤 Author

**FoxerBN**
- GitHub: [@FoxerBN](https://github.com/FoxerBN)

## 🙏 Acknowledgments

- JavaFX community for excellent documentation
- ControlsFX for enhanced UI components
- Apache PDFBox for PDF generation capabilities

---

⭐ **Star this repository if you find it helpful!** ⭐
