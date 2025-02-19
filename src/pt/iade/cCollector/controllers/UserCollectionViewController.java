package pt.iade.cCollector.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pt.iade.cCollector.WindowManager;
import pt.iade.cCollector.models.Collection;
import javafx.scene.control.cell.PropertyValueFactory;
import pt.iade.cCollector.models.User;

/** This controls the user collection view screen
 * this screen shows the user all of their collections and allows them to choose one to see in more detail **/

public class UserCollectionViewController {
	User user;

	@FXML
	TableView<Collection> collectionv = new TableView<Collection>();
	@FXML
	Button backButton;
	@FXML
	TableColumn<String, Collection> collectionColumn = new TableColumn<>("Name");
	@FXML
	TableColumn<String, Collection> descriptionColumn = new TableColumn<>("Description");	
	@FXML
	Button addCollection;	
	
	
	public UserCollectionViewController(User user) {
		this.user = user;
	}
	
	
	/** Shows all of the users collections. **/

	public void initialize() {
		ObservableList<Collection> collections = (ObservableList<Collection>) user.getCollections();
		collectionColumn.setCellValueFactory(new PropertyValueFactory<String, Collection>("catalogueName"));
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<String, Collection>("catalogueDescription"));
		collectionv.setItems(collections);
		
	   	/** If the users have collections, allows them to select one to view in the browse collection view
		 * if the user doesn't, it opens the choose collection view so that the user can choose one **/
		collectionv.setOnMouseClicked(
				(event)->{ 
			Collection selectedCollection = collectionv.getSelectionModel().getSelectedItem();
				WindowManager.openBrowseCollectionView(user, selectedCollection);
				});
		if(collections.isEmpty()) {
			collectionv.setOnMouseClicked((event)->{
				WindowManager.openChooseCollectionView(user);
			});
		}
	}
	
	/** takes you back to the login menu **/
	
	public void backClick() {
		WindowManager.backToMainWindow();
	}
	
	/** takes you to the add Collection screen
	 * allowing the user to add a new collection.
	 */
	
	public void addCollection() {
		WindowManager.openChooseCollectionView(user);
	}

}
