	$(document).ready(function(){
		jQuery('[data-toggle="tooltipNews"]').tooltip(
                {title: "<div><a href='../front/news_frontpage_cooper/newsfront.jsp'>房市新聞</a></div><div><a href='../front/news_frontpage_cooper/annfront.jsp'>系統公告</a></div>",
                 html: true,
                 delay:{"show":100,"hide":1000
             }});
        });
