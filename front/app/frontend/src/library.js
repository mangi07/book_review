import Book from './book.js'

function Library(props) {
	const books = props.library.books
	console.log(books)
  return (
		books.map( 
      (book) => {
				return (
          <Book book={book} />
				)
			}
		)
	)
}

export default Library;
