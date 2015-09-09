package bg.bulsi.internal.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "News")
@NamedQueries({
	@NamedQuery(name = "News.selectAll", query = "SELECT n FROM News n"),
	@NamedQuery(name = "News.selectBefore", query = "SELECT n FROM News n WHERE n.date > :date")})
public class News {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String title;
	
	private String content;
	
	private Date date;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Tag> tags;
	
	public News() {
		
	}
	
	public News(String title, String content, Date date, LinkedList<Tag> tags) {
		this.title = title;
		this.content = content;
		this.date = date;
		this.tags = tags;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
		
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getDate() {
		return date;
	}
	
	public List<Tag> getTags() {
		return tags;
	}
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		News other = (News) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		}
		else if (!content.equals(other.content))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		}
		else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		}
		else if (!title.equals(other.title))
			return false;
		return true;
	}	
}
