package demo.repository

import demo.model.Receipt
import org.springframework.data.repository.CrudRepository

interface ReceiptRepository : CrudRepository<Receipt, Long>

