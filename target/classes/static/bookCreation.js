var createBook = {
    setup: function(){
        $(document).on('click', '#submit', createBook.PostBook);
    }
    ,PostBook: function(e){
        e.preventDefault();
        var input = $("input");
        var params = {
            name:$("#name").val(),
            author:$("#author").val(),
            publisher:$("#publisher").val(),
            ISBN:$("#ISBN").val(),
            price:$("#cost").val(),
            inventory:$("#inventory").val(),
            description:$("#description").val()
        };
        console.log(JSON.stringify(params));
        var uri = "/books";

        $.ajax({
            method: "POST",
            url: uri,
            contentType: 'application/json',
            data: JSON.stringify(params),
            success: createBook.GetBooks
        })
    }
    ,GetBooks: function(){
        $.ajax({
            method: "GET",
            url: "/books",
            dataType: 'json',
            success: createBook.showResult
        })
    }
    ,showResult: function(jsonData){
        $('#form').trigger("reset")
        var books = jsonData._embedded.books;

        var list = document.createElement("ul");
        for( book in books) {
            var text = "Name: " + books[book].name;
            var listItem = document.createElement("li");
            var textItem = document.createTextNode(text);
            listItem.appendChild(textItem);
            list.appendChild(listItem);
        }
        var elem = document.getElementById("sixNine")
        elem.appendChild(list);
    }
}

$(document).ready(function (){
    createBook.setup()
})
