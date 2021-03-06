package tane.mahuta.buildtools.dependency;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;

/**
 * Represents a resolved artifact.
 *
 * @author christian.heike@icloud.com
 *         Created on 22.06.17.
 */
public interface Artifact {

    /**
     * @return the descriptor of the
     */
    @Nonnull
    GAVCDescriptor getDescriptor();

    /**
     * @return the locale file for the resolved artifact
     */
    @Nullable
    File getLocalFile();

    /**
     * @return {@code true} if this artifact is built by the project, {@code false} otherwise
     */
    boolean isInternalArtifact();
}
