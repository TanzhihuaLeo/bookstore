package com.qrot.bookstore.background.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;

import com.qrot.bookstore.background.model.Book;
/**
 * Mapper
 * 
 * @author TanZhiHua
 *
 */
@Mapper
public interface BookMapper {

	/**
	 * 增加书籍的基本信息
	 * @param book
	 */
	@Insert("insert into book_info(book_isbn,book_name,book_author,book_publish,book_kind,book_summary,book_publtime,book_cover) "
			+ "values(#{isbn},#{bookname},#{author},#{publish},#{kind},#{summary},#{publtime},#{cover})")
	void addBookInfo(Book book);
	
	/**
	 * 增加书籍变动信息
	 * @param book
	 */
	@Insert("insert into book(book_id,book_price,book_storage,book_volume,book_score,book_delete) "
			+ "values(LAST_INSERT_ID(),#{price},#{storage},#{volume},#{score},#{delete})")
	void addBook(Book book);
	
	/**
	 * 修改书籍基本数据
	 * @param book
	 */
	@Update("update book_info set book_author=#{author}, book_publish=#{publish}, book_kind=#{kind},"
			+ "book_summary=#{summary}, book_publtime=#{publtime} where book_id=#{bookid}")
	void updateBookInfo(Book book);
	
	/**
	 * 修改书籍变动信息
	 * @param book
	 */
	@Update("update book set book_price=#{price},book_storage=#{storage} where book_id=#{bookid}")
	void updateBook(Book book);
	
	/**
	 * 书籍下架
	 * @param bookid
	 */
	@Update("update book set book_delete = 1 where book_id=#{bookid}")
	void removeBook(int bookid);
	
	/**
	 * 书籍上架
	 * @param bookid
	 */
	@Update("update book set book_delete = 0 where book_id=#{bookid}")
	void recoverBook(int bookid);
	
	/**
	 * 得到指定的那本书的全部信息
	 * @param bookid
	 * @return
	 */
	@Select("select * from v_book where book_id =#{bookid}")
	@Results(id="book", value = { 
			@Result(column = "book_id", property = "bookid"),  
			@Result(column = "book_isbn", property = "isbn"),
			@Result(column = "book_name", property = "bookname"), 
			@Result(column = "book_author", property = "author"),
			@Result(column = "book_publish", property = "publish"), 
			@Result(column = "book_kind", property = "kind"),
			@Result(column = "book_summary", property = "summary"),
			@Result(column = "book_publtime", property = "publtime"),
			@Result(column = "book_cover", property = "cover"), 
			@Result(column = "book_price", property = "price"),
			@Result(column = "book_storage", property = "storage"),
			@Result(column = "book_volume", property = "volume"), 
			@Result(column = "book_score", property = "score"),
			@Result(column = "book_delete", property = "delete"),})
	Book getBookByID(int bookid);
	
	/**
	 * 统计书籍数量
	 * @return
	 */
	@Select("select count(book_id) from v_book")
	int getAllBookLen();
	
	/**
	 * 显示指定行数的全部书籍信息
	 * @param len  长度
	 * @param offset  偏移量
	 * @return
	 */
	@Select("select * from v_book limit #{len} offset #{offset}")
	@ResultMap("book")
	List<Book> getAllBook(@Param("len") int len, @Param("offset")int offset);
	
	/**
	 * 统计指定类别的书籍数量
	 * @param kind
	 * @return
	 */
	@Select("select count(book_id) from v_book where book_kind=#{kind} and book_delete=0")
	int getKindBookLen(@Param("kind")String kind);
	
	/**
	 * 分类显示书籍
	 * @param kind  类别
	 * @param len  长度
	 * @param offset  偏移量
	 * @return
	 */
	@Select("select * from v_book where book_kind=#{kind} and book_delete=0 limit #{len} offset #{offset}")
	@ResultMap("book")
	List<Book> getKindBook (
			@Param("kind")String kind,
			@Param("len") int len, 
			@Param("offset")int offset);
	
	/**
	 * 得到书籍的所有分类
	 * @return
	 */
	@Select("select distinct book_kind from book_info")
	List<String> getkind();
	
	/**
	 * 统计书籍模糊查询结果数量
	 * @param text
	 * @return
	 */
	@Select("select count(book_id) from v_book where book_delete=0 and (book_name regexp #{text} "
			+ "or book_author regexp #{text} "
			+ "or book_publish regexp #{text} "
			+ "or book_kind regexp #{text})")
	int getSelectBookLen(@Param("text") String text);
	
	/**
	 *  模糊查询搜索书籍
	 * @param text
	 * @param len
	 * @param offset
	 * @return
	 */
	@Select("select * from v_book where book_delete=0 and (book_name regexp #{text} "
			+ "or book_author regexp #{text} "
			+ "or book_publish regexp #{text} "
			+ "or book_kind regexp #{text})"
			+ "limit #{len} offset #{offset}")
	@ResultMap("book")
	List<Book> selectBook(
			@Param("text") String text, 
			@Param("len") int len, 
			@Param("offset")int offset);
	
	/**
	 * 统计未上架书籍数量
	 * @return
	 */
	@Select("select count(book_id) from v_book where book_delete=1")
	int getDeleteBookLen();
	
	/**
	 * 查询未上架书籍
	 * @param len
	 * @param offset
	 * @return
	 */
	@Select("select * from v_book where book_delete=1 limit #{len} offset #{offset}")
	@ResultMap("book")
	List<Book> getDeleteBook(@Param("len") int len, @Param("offset")int offset);
	
	/**
	 * 统计已上架书籍数量
	 * @return
	 */
	@Select("select count(book_id) from v_book where book_delete=0")
	int getSellingBookLen();
	
	/**
	 * 查询已上架书籍
	 * @param len
	 * @param offset
	 * @return
	 */
	@Select("select * from v_book where book_delete=0 limit #{len} offset #{offset}")
	@ResultMap("book")
	List<Book> getSellingBook(@Param("len") int len, @Param("offset")int offset);
	
	/**
	 * 统计库存低于某值的已上架书籍数量
	 * 
	 * @param low
	 * @return
	 */
	@Select("select count(book_id) from v_book where book_delete=0 and book_storage<=#{low}")
	int getLowerStorageLen(@Param("low")int low);
	
	/**
	 * 查询库存低于某值的书籍
	 * @param low
	 * @param len
	 * @param offset
	 * @return
	 */
	@Select("select * from v_book where book_delete=0 and book_storage <= #{low} limit #{len} offset #{offset}")
	@ResultMap("book")
	List<Book> getLowerStorage(
			@Param("low")int low, 
			@Param("len") int len, 
			@Param("offset")int offset);
}
