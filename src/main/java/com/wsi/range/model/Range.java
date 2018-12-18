package com.wsi.range.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("ranges")
public class Range
{
	@PrimaryKey
	private UUID id;
	private Integer left;
	private Integer right;

	public Range(UUID id, Integer left, Integer right)
	{
		this.id = id;
		this.left = left;
		this.right = right;
	}

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}

	public Integer getLeft()
	{
		return left;
	}

	public void setLeft(Integer left)
	{
		this.left = left;
	}

	public Integer getRight()
	{
		return right;
	}

	public void setRight(Integer right)
	{
		this.right = right;
	}

	@Override
	public String toString()
	{
		return "Range [id=" + id + ", left=" + left + ", right=" + right + "]";
	}

	/**
	 * This method processes all zipcode ranges from DB
	 * 
	 * @param ranges
	 *            - List of all ranges from DB
	 * 
	 * @return list of processed ranges
	 */
	public static List<Range> processRanges(List<Range> ranges)
	{
		List<Range> processedRanges = new ArrayList<Range>();

		// loop over entire Range collection
		for (Range range : ranges)
		{
			boolean expanded = false;

			for (Range processedRange : processedRanges)
			{
				if (processedRange.isRangeExpandable(range))
				{
					processedRange.expandBoudaries(range);
					expanded = true;
					break;
				}
			}

			if (!expanded)
				processedRanges.add(range);

		}
		
		return processedRanges;
	}

	/**
	 * This method evaluates if boundaries of this Range instance can be expanded.
	 * 
	 * @param range
	 *            - the Range object to evaluate
	 * 
	 * @return true if any boundary overlapped, otherwise false
	 */
	public boolean isRangeExpandable(Range range)
	{
		return Range.contains(this, range.left) || Range.contains(this, range.right) || Range.contains(range, this.left) || Range.contains(range, this.right);
	}

	/**
	 * This method expands boundaries any overlapped values from the specified range into this instance.
	 * 
	 * @param range
	 *            - the Range object to evaluate against this instance
	 */
	public void expandBoudaries(Range range)
	{
		this.left = range.left < this.left ? range.left : this.left;
		this.right = range.right > this.right ? range.right : this.right;
	}

	/**
	 * This method checks if range contains specific zipcode
	 * 
	 * @param range
	 *            - the range to be tested for specific zipcode
	 * @param zipcode
	 *            - zipcode to test within the range
	 * 
	 * @return true if zipcode is within boundaries of the range, otherwise false
	 */
	public static boolean contains(Range range, int zipcode)
	{
		return range.getLeft() <= zipcode && zipcode <= range.getRight();
	}
}
