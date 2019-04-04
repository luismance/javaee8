class SellerList extends React.Component {

	constructor(props) {
	    super(props);
	    this.state = {
	    sellerurl : '',
	      sellername : '',
	      items : []
	    };
	    
	    this.componentDidMount = this.componentDidMount.bind(this);
	  }
	
	componentDidMount(){
		var thisComp = this;
		if(thisComp.state.sellerurl){
		$.ajax({
		      type: "GET",
		      url: "rest/rest/parse/tipidpc?url=" + this.state.sellerurl,
		      headers: {
		        "Content-Type": "application/json"
		      },
		      dataType: "text",
		      success: function(data) {
		        var sellerDetails = data.itemsForSale;
		        thisComp.setState({ items : sellerDetails });
		      },
		      error: function(data) {
		        console.log("Error : " + JSON.stringify(data));
		      }
		    });
	}
	}
	
	render(){
		return(
		
				<div>
					<label>Input URL : </label><input id="websiteurl" type="text" />
				</div>
		)
	}
}
ReactDOM.render(<SellerList />,document.getElementById('app'));