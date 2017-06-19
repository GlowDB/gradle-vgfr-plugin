package tane.mahuta.buildtools.version;


import lombok.EqualsAndHashCode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Supplier;

/**
 * {@link DefaultSemanticVersion} using a branch qualifier.
 *
 * @author christian.heike@icloud.com
 *         Created on 06.06.17.
 */
@EqualsAndHashCode(callSuper = true)
public class DefaultSemanticBranchVersion extends AbstractSemanticVersion implements SemanticBranchVersion {

    protected final String qualifier;
    protected final Supplier<String> branchQualifierSupplier;

    /**
     * Create a new semantic branch version of the provided parameters.
     *
     * @param major                   the major version part
     * @param minor                   the minor version part
     * @param micro                   the micro version part
     * @param branchQualifierSupplier the supplier for the branch qualifier
     * @param qualifier               the qualifier for the version
     */
    public DefaultSemanticBranchVersion(int major, int minor, @Nullable Integer micro, @Nonnull final Supplier<String> branchQualifierSupplier,
                                        @Nullable final String qualifier) {
        super(major, minor, micro);
        this.branchQualifierSupplier = branchQualifierSupplier;
        this.qualifier = qualifier;
    }


    @Nullable
    @Override
    public String getBranchQualifier() {
        return branchQualifierSupplier.get();
    }

    @Override
    protected String toStringRepresentation() {
        return AbstractSemanticVersion.createStringRepresentation(major, minor, micro, branchQualifierSupplier.get(), qualifier);
    }

    @Override
    public int compareTo(final SemanticVersion other) {
        final int result = super.compareTo(other);
        if (result != 0) {
            return result; // If the version already differs, return it
        }
        final String thatBranchQualifier = other instanceof SemanticBranchVersion ? ((SemanticBranchVersion) other).getBranchQualifier() : null;
        if (getBranchQualifier() == null) {
            return thatBranchQualifier == null ? 0 : 1;
        } else {
            return thatBranchQualifier != null ? 0 : -1;
        }
    }

    @Override
    public String getQualifier() {
        return qualifier;
    }

}