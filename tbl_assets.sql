/*
Navicat SQLite Data Transfer

Source Server         : rz_erp_copy
Source Server Version : 30808
Source Host           : :0

Target Server Type    : SQLite
Target Server Version : 30808
File Encoding         : 65001

Date: 2020-01-01 20:22:21
*/

PRAGMA foreign_keys = OFF;

-- ----------------------------
-- Table structure for tbl_assets
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_assets";
CREATE TABLE "tbl_assets" (
"id"  INTEGER NOT NULL,
"asset_name"  TEXT,
"type"  TEXT,
"unit_value"  INTEGER,
"entry_time"  TEXT,
"location"  TEXT,
"quantity"  INTEGER,
"unit"  TEXT,
PRIMARY KEY ("id" ASC)
);

-- ----------------------------
-- Table structure for tbl_bank_statement
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_bank_statement";
CREATE TABLE "tbl_bank_statement" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL DEFAULT 0,
"desc"  TEXT,
"total"  INTEGER,
"type"  TEXT,
"date"  TEXT,
"is_inflow"  INTEGER,
"who"  TEXT,
"deduct_ref"  TEXT
);

-- ----------------------------
-- Table structure for tbl_bank_statements
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_bank_statements";
CREATE TABLE "tbl_bank_statements" ("account" TEXT,"account_name" TEXT,"transanction_time" TEXT,"debit_amount" TEXT,"credit_amount" TEXT,"balence" TEXT,"currency" TEXT,"reciprocal_account_name" TEXT,"reciprocal_account" TEXT,"reciprocal_account_bank" TEXT,"date_recorded" TEXT,"summary" TEXT,"remark" TEXT,"transaction_flow_number" TEXT,"enterprice_flow_number" TEXT,"credential_type" TEXT,"credential" TEXT,"个性化信息名称1" TEXT,"个性化信息名称2" TEXT,"个性化信息名称3" TEXT,"个性化信息名称4" TEXT,"个性化信息名称5" TEXT,"个性化信息名称6" TEXT,"个性化信息名称7" TEXT,"个性化信息名称8" TEXT,"个性化信息名称9" TEXT,"个性化信息名称10" TEXT,"个性化信息1" TEXT,"个性化信息2" TEXT,"个性化信息3" TEXT,"个性化信息4" TEXT,"个性化信息5" TEXT,"个性化信息6" TEXT,"个性化信息7" TEXT,"个性化信息8" TEXT,"个性化信息9" TEXT,"个性化信息10" TEXT);

-- ----------------------------
-- Table structure for tbl_client
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_client";
CREATE TABLE "tbl_client" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"contact"  TEXT,
"tel"  TEXT,
"addr"  TEXT,
"tag"  TEXT,
"tax_number"  INTEGER,
"bank"  TEXT,
"bank_account"  TEXT,
"dept_code"  TEXT
);

-- ----------------------------
-- Table structure for tbl_client_contact
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_client_contact";
CREATE TABLE "tbl_client_contact" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"contact"  TEXT,
"tel"  TEXT,
"addr"  TEXT,
"role"  TEXT,
"email"  TEXT,
"client_id"  INTEGER,
"isDefault"  INTEGER DEFAULT 1
);

-- ----------------------------
-- Table structure for tbl_client_inventory
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_client_inventory";
CREATE TABLE "tbl_client_inventory" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"inventory_code"  TEXT,
"description"  TEXT,
"client_id"  INTEGER,
"product_id"  INTEGER,
"unit"  TEXT DEFAULT 套
);

-- ----------------------------
-- Table structure for tbl_client_role_default
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_client_role_default";
CREATE TABLE "tbl_client_role_default" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"role_id"  TEXT,
"client_contact_id"  TEXT,
"client_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_delivery
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_delivery";
CREATE TABLE "tbl_delivery" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"fee"  INTEGER,
"date"  TEXT,
"logistics_company"  TEXT,
"waybill_number"  TEXT,
"document_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_deposit
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_deposit";
CREATE TABLE "tbl_deposit" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  INTEGER,
"date"  TEXT,
"summary"  TEXT,
"tras_ref"  TEXT,
"from"  TEXT
);

-- ----------------------------
-- Table structure for tbl_employee
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_employee";
CREATE TABLE "tbl_employee" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"role"  TEXT,
"base_salary"  REAL
);

-- ----------------------------
-- Table structure for tbl_expenditure
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_expenditure";
CREATE TABLE "tbl_expenditure" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  INTEGER,
"date"  TEXT,
"summary"  TEXT,
"payer"  TEXT,
"expenditure_type_id"  TEXT,
"order_num"  TEXT,
"invoice_id"  INTEGER,
"proof_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_expenditure_reimbursement
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_expenditure_reimbursement";
CREATE TABLE "tbl_expenditure_reimbursement" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"expenditure_id"  INTEGER,
"reimbursement_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_invoice
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_invoice";
CREATE TABLE "tbl_invoice" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"type_code"  TEXT,
"number"  TEXT,
"tax_total"  INTEGER,
"issue_date"  TEXT,
"summary"  TEXT,
"type"  INTEGER,
"is_input"  INTEGER DEFAULT 1,
"submit_date"  TEXT,
"price_total_with_tax"  INTEGER,
"issuer"  INTEGER,
"proof_id"  INTEGER,
"category"  TEXT
);

-- ----------------------------
-- Table structure for tbl_liability
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_liability";
CREATE TABLE "tbl_liability" (
"id"  INTEGER NOT NULL,
"item"  TEXT,
"payee"  TEXT,
"total"  INTEGER,
PRIMARY KEY ("id" ASC)
);

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_order";
CREATE TABLE "tbl_order" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  REAL,
"date"  TEXT,
"summary"  TEXT,
"tras_ref"  INTEGER,
"order_num"  TEXT,
"invoice_id"  INTEGER,
"client_id"  INTEGER,
"status"  TEXT,
"note"  TEXT,
"tax_rate"  INTEGER DEFAULT 13,
"invoice_date"  TEXT,
"currency_id"  INTEGER DEFAULT 0,
"receive_ref"  TEXT,
"order_dept"  TEXT,
CONSTRAINT "u_order_number" UNIQUE ("order_num" ASC)
);

-- ----------------------------
-- Table structure for tbl_order_copy
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_order_copy";
CREATE TABLE "tbl_order_copy" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  REAL,
"date"  TEXT,
"summary"  TEXT,
"tras_ref"  INTEGER,
"order_num"  TEXT,
"invoice_id"  INTEGER,
"client_id"  INTEGER,
"status"  INTEGER,
"note"  TEXT,
"tax_rate"  INTEGER DEFAULT 13,
"invoice_date"  TEXT,
"currency_id"  INTEGER DEFAULT 0,
"receive_ref"  TEXT,
"order_dept"  TEXT,
CONSTRAINT "u_order_number" UNIQUE ("order_num" ASC)
);

-- ----------------------------
-- Table structure for tbl_order_copy1_test
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_order_copy1_test";
CREATE TABLE "tbl_order_copy1_test" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  REAL,
"date"  TEXT,
"summary"  TEXT,
"tras_ref"  INTEGER,
"order_num"  TEXT,
"invoice_id"  INTEGER,
"client_id"  INTEGER,
"status"  INTEGER,
"note"  TEXT,
"tax_rate"  INTEGER DEFAULT 13,
"invoice_date"  TEXT,
"currency_id"  INTEGER DEFAULT 0,
"receive_ref"  TEXT,
"order_dept"  TEXT,
CONSTRAINT "u_order_number" UNIQUE ("order_num" ASC)
);

-- ----------------------------
-- Table structure for tbl_order_invoicing
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_order_invoicing";
CREATE TABLE "tbl_order_invoicing" (
"id"  INTEGER NOT NULL,
"order_id"  INTEGER,
"date"  TEXT,
"delivery_id"  INTEGER,
"invoices_id"  INTEGER,
PRIMARY KEY ("id")
);

-- ----------------------------
-- Table structure for tbl_order_item
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_order_item";
CREATE TABLE "tbl_order_item" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  INTEGER,
"qty"  TEXT,
"desc"  TEXT,
"sale_price"  INTEGER,
"order_id"  TEXT,
"customer_item_no"  TEXT,
"product_id"  INTEGER,
"cost_price"  INTEGER DEFAULT 0
);

-- ----------------------------
-- Table structure for tbl_order_item_test
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_order_item_test";
CREATE TABLE "tbl_order_item_test" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  INTEGER,
"qty"  TEXT,
"desc"  TEXT,
"sale_price"  INTEGER,
"order_id"  TEXT,
"customer_item_no"  TEXT,
"product_id"  INTEGER,
"cost_price"  INTEGER DEFAULT 0
);

-- ----------------------------
-- Table structure for tbl_payment_receipt
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_payment_receipt";
CREATE TABLE "tbl_payment_receipt" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"amount_total"  INTEGER,
"date"  TEXT,
"consignee"  TEXT,
"waybill_number"  TEXT,
"document_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_payroll
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_payroll";
CREATE TABLE "tbl_payroll" (
"id"  INTEGER NOT NULL,
"base_salary"  REAL,
"bonus"  REAL,
"pay_date"  TEXT,
"employee_id"  INTEGER,
""  TEXT,
PRIMARY KEY ("id")
);

-- ----------------------------
-- Table structure for tbl_procurement
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_procurement";
CREATE TABLE "tbl_procurement" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  INTEGER,
"date"  TEXT,
"summary"  TEXT,
"tras_ref"  INTEGER,
"order_id"  TEXT,
"invoice_id"  INTEGER,
"po_number"  TEXT,
"supplier_id"  INTEGER,
"status"  TEXT,
"note"  TEXT,
"to_pay"  INTEGER DEFAULT 0,
CONSTRAINT "u_po_number" UNIQUE ("po_number" ASC)
);

-- ----------------------------
-- Table structure for tbl_procurement_item
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_procurement_item";
CREATE TABLE "tbl_procurement_item" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  INTEGER,
"qty"  TEXT,
"desc"  TEXT,
"sale_price"  INTEGER,
"procuremnet_id"  TEXT,
"customer_item_no"  TEXT,
"product_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_procurement_trans
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_procurement_trans";
CREATE TABLE "tbl_procurement_trans" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"procurement_id"  INTEGER,
"transaction_id"  INTEGER,
"desc"  TEXT,
"addr"  TEXT,
"time"  TEXT,
"ref_no"  INTEGER,
"total"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_product
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_product";
CREATE TABLE "tbl_product" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"date"  TEXT,
"sale_price"  INTEGER,
"cost_price"  INTEGER,
"loweast_price"  INTEGER,
"name"  TEXT,
"spec"  TEXT,
"associate_id"  INTEGER,
"type"  INTEGER,
"unit"  TEXT DEFAULT 个,
"note"  TEXT
);

-- ----------------------------
-- Table structure for tbl_product_price
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_product_price";
CREATE TABLE "tbl_product_price" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"date"  TEXT,
"sale_price"  TEXT,
"cost_price"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_product_suit
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_product_suit";
CREATE TABLE "tbl_product_suit" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"product_id"  INTEGER,
"qty"  INTEGER,
"parent_product_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_proof
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_proof";
CREATE TABLE "tbl_proof" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"date"  TEXT,
"name"  TEXT,
"path"  TEXT
);

-- ----------------------------
-- Table structure for tbl_reimbursement 
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_reimbursement ";
CREATE TABLE "tbl_reimbursement " (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  INTEGER,
"date"  TEXT,
"summary"  TEXT,
"tras_ref"  TEXT
);

-- ----------------------------
-- Table structure for tbl_shippment
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_shippment";
CREATE TABLE "tbl_shippment" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"amount_total"  INTEGER,
"date"  TEXT,
"consignee"  TEXT,
"waybill_number"  TEXT,
"document_id"  INTEGER
);

-- ----------------------------
-- Table structure for tbl_supplier
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_supplier";
CREATE TABLE "tbl_supplier" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"name"  TEXT,
"contact"  TEXT,
"tel"  TEXT,
"addr"  TEXT,
"tag"  TEXT,
"bank_account"  TEXT,
"bank_account_no"  TEXT,
"bank_branch"  TEXT
);

-- ----------------------------
-- Table structure for tbl_transaction
-- ----------------------------
DROP TABLE IF EXISTS "main"."tbl_transaction";
CREATE TABLE "tbl_transaction" (
"id"  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
"total"  INTEGER,
"bank"  TEXT,
"tel"  TEXT,
"addr"  TEXT,
"time"  TEXT,
"ref_no"  TEXT,
"summary"  TEXT
);
