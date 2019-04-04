class SellerList extends React.Component {

	constructor(props) {
	    super(props);
	    this.state = {
	    sellerurl : '',
	      sellername : '',
	      items : [],
				error : '',
				errorInfo : '',
				pageNum : 1,
				pageSize : 10
	    };
	  }

	updateItems = (pageNum, pageSize) => {
		var thisComp = this;
		var sellerUrl = $('#websiteurl').val();
		console.log("Updating Items from "+sellerUrl);
		if(sellerUrl){
		$.ajax({
		      type: "GET",
		      url: "rest/parse/tipidpc?url=" + sellerUrl+"&pageNum="+pageNum+"&pageSize="+pageSize,
		      headers: {
		        "Content-Type": "application/json"
		      },
		      dataType: "text",
		      success: function(data) {
						var dataObj = JSON.parse(data);
						var sellerId = dataObj.id;
		        var sellerDetails = dataObj.itemsForSale;
		        thisComp.setState({
							sellername : dataObj.id,
							pageNum,
							pageSize,
							items : sellerDetails });
		      },
		      error: function(data) {
		        console.log("Error : " + JSON.stringify(data));
		      }
		    });
			}
	}

	componentDidCatch(error, errorInfo) {
    this.setState({
      error: error,
			errorInfo: errorInfo,
    })
  }

	render(){
		var itemList = this.state.items.map((item, i) => (
      <tr key={item.id}>
				<td>
					{item.name}
				</td>
				<td>
					{item.price}
				</td>
				<td>
					{
						item.imgUrls.map((img, i) => (
							<img key={i} src={img} height="42" width="42" />
						))
					}
				</td>
			</tr>
    ));

		return(

				<div>
					<h3>{this.state.errorInfo ? this.state.errorInfo : ""}</h3>
					<label>Input URL : </label>
					<input id="websiteurl" type="text" />
					<button id="btnUpdateItem"  onClick={() => this.updateItems(1,10)}>Update List</button>
					<h3>{this.state.sellername}</h3>
					<h4>page : {this.state.pageNum}</h4>
					<table width="100%">
					<tbody>
					{itemList}
					</tbody>
					</table>
					<a style={{ visibility : (this.state.pageNum > 1 ? "visible" : "hidden") }} onClick={() => this.updateItems(this.state.pageNum-1,this.state.pageSize)} >{"<"}</a>
					<a onClick={() => this.updateItems(this.state.pageNum+1,this.state.pageSize)}>></a>

				</div>
		)
	}
}
ReactDOM.render(<SellerList />,document.getElementById('app'));
