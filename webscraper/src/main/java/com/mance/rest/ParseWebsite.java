package com.mance.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.mance.model.Item;
import com.mance.model.Seller;

@Path("/parse")
public class ParseWebsite {

	@GET
	@Path("/tipidpc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getElements(@QueryParam(value = "url") String url) {

		try {
			System.out.println("Accessing : " + url);
			Document doc = Jsoup.connect(url).get();
			if (doc.select("h1.username").size() > 0) {
				Elements sellerUsername = doc.select("h1.username");
				Elements itemsForSale = doc.select("ul#user-ifs > li");

				String sellerId = sellerUsername.get(0).text();

				Seller seller = new Seller(sellerId, url);

				List<Item> userItems = new ArrayList<Item>();
				for (int i = 0; i < itemsForSale.size(); i++) {
					Element currentItem = itemsForSale.get(i);
					String itemId = currentItem.select("h4 > a").attr("href").split("iid=")[1];
					String itemName = currentItem.select("h4 > a").text();
					String itemPrice = currentItem.select("div.meta > strong").text();
					Item item = new Item(Integer.parseInt(itemId), itemName, Double.parseDouble(itemPrice.split(" ")[1]));
					userItems.add(item);
				}
				seller.setItemsForSale(userItems);
				return Response.ok().entity(seller).build();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} catch (IndexOutOfBoundsException e) {
			e.getMessage();
		}

		return Response.serverError().entity("Accessing : " + url + " failed, url must be formed as https://tipidpc.com/useritems.php?username={{username}}").build();
	}
}
