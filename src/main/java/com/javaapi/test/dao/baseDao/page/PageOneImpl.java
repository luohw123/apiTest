package com.javaapi.test.dao.baseDao.page;


import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Basic {@code Page} implementation.
 *
 * @param <T> the type of which the page consists.
 * @author Oliver Gierke
 */
public class PageOneImpl<T> extends ChunkOne<T> implements Page<T> {

    private static final long serialVersionUID = 867755909294344406L;

    private final long total;
    private final Pageable pageable;

    /**
     * Constructor of {@code PageImpl}.
     *
     * @param content the content of this page, must not be {@literal null}.
     * @param pageable the paging information, can be {@literal null}.
     * @param total the total amount of items available. The total might be adapted considering the length of the content
     *          given, if it is going to be the content of the last page. This is in place to mitigate inconsistencies
     */
    public PageOneImpl(List<T> content, Pageable pageable, long total) {

        super(content, pageable);

        this.pageable = pageable;
        this.total = !content.isEmpty() && pageable != null && pageable.getOffset() + pageable.getPageSize() > total
                ? pageable.getOffset() + content.size() : total;
    }

    /**
     * Creates a new {@link PageOneImpl} with the given content. This will result in the created {@link Page} being identical
     * to the entire {@link List}.
     *
     * @param content must not be {@literal null}.
     */
    public PageOneImpl(List<T> content) {
        this(content, null, null == content ? 0 : content.size());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getTotalPages()
     */
    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Page#getTotalElements()
     */
    @Override
    public long getTotalElements() {
        return total;
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#hasNext()
     */
    @Override
    public boolean hasNext() {
        return getNumber() < getTotalPages();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#isLast()
     */
    @Override
    public boolean isLast() {
        return !hasNext();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.data.domain.Slice#transform(org.springframework.core.convert.converter.Converter)
     */
    @Override
    public <S> Page<S> map(Converter<? super T, ? extends S> converter) {
        return new PageOneImpl<S>(getConvertedContent(converter), pageable, total);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        String contentType = "UNKNOWN";
        List<T> content = getContent();

        if (content.size() > 0) {
            contentType = content.get(0).getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", getNumber() + 1, getTotalPages(), contentType);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PageOneImpl<?>)) {
            return false;
        }

        PageOneImpl<?> that = (PageOneImpl<?>) obj;

        return this.total == that.total && super.equals(obj);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {

        int result = 17;

        result += 31 * (int) (total ^ total >>> 32);
        result += 31 * super.hashCode();

        return result;
    }
}