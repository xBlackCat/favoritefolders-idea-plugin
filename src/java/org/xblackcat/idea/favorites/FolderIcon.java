package org.xblackcat.idea.favorites;

import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.ui.ImageUtil;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author xBlackCat
 */

@SuppressWarnings("UnusedDeclaration")
enum FolderIcon implements IIconGetter {
    Default(IconLoader.getIcon("/nodes/folder.svg")),
    Custom(IconLoader.getIcon("/nodes/newFolder.png")),
    AbstractClass(IconLoader.getIcon("/nodes/abstractClass.svg")),
    AbstractException(IconLoader.getIcon("/nodes/abstractException.svg")),
    AbstractMethod(IconLoader.getIcon("/nodes/abstractMethod.svg")),
    AnnotationType(IconLoader.getIcon("/nodes/annotationtype.svg")),
    AnonymousClass(IconLoader.getIcon("/nodes/anonymousClass.svg")),
    Artifact(IconLoader.getIcon("/nodes/artifact.svg")),
    C_plocal(IconLoader.getIcon("/nodes/c_plocal.svg")),
    C_private(IconLoader.getIcon("/nodes/c_private.svg")),
    C_protected(IconLoader.getIcon("/nodes/c_protected.svg")),
    C_public(IconLoader.getIcon("/nodes/c_public.svg")),
    Class(IconLoader.getIcon("/nodes/class.svg")),
    ClassInitializer(IconLoader.getIcon("/nodes/classInitializer.svg")),
    CompiledClassesFolder(IconLoader.getIcon("/nodes/compiledClassesFolder.svg")),
    CopyOfFolder(IconLoader.getIcon("/nodes/copyOfFolder.svg")),
    Cvs_global(IconLoader.getIcon("/nodes/cvs_global.svg")),
    Cvs_roots(IconLoader.getIcon("/nodes/cvs_roots.svg")),
    DataColumn(IconLoader.getIcon("/nodes/dataColumn.png")),
    DataSchema(IconLoader.getIcon("/nodes/dataSchema.png")),
    dataSource(IconLoader.getIcon("/nodes/DataSource.png")),
    dataTables(IconLoader.getIcon("/nodes/DataTables.png")),
    DataView(IconLoader.getIcon("/nodes/dataView.png")),
    Deploy(IconLoader.getIcon("/nodes/deploy.png")),
    Ejb(IconLoader.getIcon("/nodes/ejb.png")),
    EjbBusinessMethod(IconLoader.getIcon("/nodes/ejbBusinessMethod.png")),
    EjbCmpField(IconLoader.getIcon("/nodes/ejbCmpField.png")),
    EjbCmrField(IconLoader.getIcon("/nodes/ejbCmrField.png")),
    EjbCreateMethod(IconLoader.getIcon("/nodes/ejbCreateMethod.png")),
    EjbFinderMethod(IconLoader.getIcon("/nodes/ejbFinderMethod.png")),
    EjbPrimaryKeyClass(IconLoader.getIcon("/nodes/ejbPrimaryKeyClass.png")),
    EjbReference(IconLoader.getIcon("/nodes/ejbReference.png")),
    EnterpriseProject(IconLoader.getIcon("/nodes/enterpriseProject.png")),
    Enum(IconLoader.getIcon("/nodes/enum.png")),
    ErrorIntroduction(IconLoader.getIcon("/nodes/errorIntroduction.png")),
    ExceptionClass(IconLoader.getIcon("/nodes/exceptionClass.png")),
    ExtractedFolder(IconLoader.getIcon("/nodes/extractedFolder.png")),
    Field(IconLoader.getIcon("/nodes/field.png")),
    FieldPK(IconLoader.getIcon("/nodes/fieldPK.png")),
    Folder(IconLoader.getIcon("/nodes/folder.png")),
    Function(IconLoader.getIcon("/nodes/function.png")),
    HomeFolder(IconLoader.getIcon("/nodes/homeFolder.png")),
    IdeaModule(IconLoader.getIcon("/nodes/ideaModule.png")),
    IdeaProject(IconLoader.getIcon("/nodes/ideaProject.png")),
    InspectionResults(IconLoader.getIcon("/nodes/inspectionResults.png")),
    Interface(IconLoader.getIcon("/nodes/interface.png")),
    JarDirectory(IconLoader.getIcon("/nodes/jarDirectory.png")),
    JavaDocFolder(IconLoader.getIcon("/nodes/javaDocFolder.png")),
    Jsr45(IconLoader.getIcon("/nodes/jsr45.png")),
    KeymapAnt(IconLoader.getIcon("/nodes/keymapAnt.png")),
    KeymapEditor(IconLoader.getIcon("/nodes/keymapEditor.png")),
    KeymapMainMenu(IconLoader.getIcon("/nodes/keymapMainMenu.png")),
    KeymapOther(IconLoader.getIcon("/nodes/keymapOther.png")),
    KeymapTools(IconLoader.getIcon("/nodes/keymapTools.png")),
    Method(IconLoader.getIcon("/nodes/method.png")),
    Module(IconLoader.getIcon("/nodes/Module.png")),
    ModuleGroup(IconLoader.getIcon("/nodes/moduleGroup.png")),
    NewException(IconLoader.getIcon("/nodes/newException.png")),
    NewFolder(IconLoader.getIcon("/nodes/newFolder.png")),
    NewParameter(IconLoader.getIcon("/nodes/newParameter.png")),
    NodePlaceholder(IconLoader.getIcon("/nodes/nodePlaceholder.png")),
    Package(IconLoader.getIcon("/nodes/package.png")),
    Padlock(IconLoader.getIcon("/nodes/padlock.png")),
    Parameter(IconLoader.getIcon("/nodes/parameter.png")),
    Plugin(IconLoader.getIcon("/nodes/plugin.png")),
    PluginNotInstalled(IconLoader.getIcon("/nodes/pluginnotinstalled.png")),
    PluginObsolete(IconLoader.getIcon("/nodes/pluginobsolete.png")),
    Pointcut(IconLoader.getIcon("/nodes/pointcut.png")),
    PpFile(IconLoader.getIcon("/nodes/ppFile.png")),
    PpInvalid(IconLoader.getIcon("/nodes/ppInvalid.png")),
    PpJar(IconLoader.getIcon("/nodes/ppJar.png")),
    PpJdk(IconLoader.getIcon("/nodes/ppJdk.png")),
    PpLib(IconLoader.getIcon("/nodes/ppLib.png")),
    PpLibFolder(IconLoader.getIcon("/nodes/ppLibFolder.png")),
    PpWeb(IconLoader.getIcon("/nodes/ppWeb.png")),
    Project(IconLoader.getIcon("/nodes/project.png")),
    Property(IconLoader.getIcon("/nodes/property.png")),
    PropertyRead(IconLoader.getIcon("/nodes/propertyRead.png")),
    PropertyReadStatic(IconLoader.getIcon("/nodes/propertyReadStatic.png")),
    PropertyReadWrite(IconLoader.getIcon("/nodes/propertyReadWrite.png")),
    PropertyReadWriteStatic(IconLoader.getIcon("/nodes/propertyReadWriteStatic.png")),
    PropertyWrite(IconLoader.getIcon("/nodes/propertyWrite.png")),
    PropertyWriteStatic(IconLoader.getIcon("/nodes/propertyWriteStatic.png")),
    ReadAccess(IconLoader.getIcon("/nodes/read-access.png")),
    ResourceBundle(IconLoader.getIcon("/nodes/resourceBundle.png")),
    RwAccess(IconLoader.getIcon("/nodes/rw-access.png")),
    SecurityRole(IconLoader.getIcon("/nodes/SecurityRole.png")),
    Servlet(IconLoader.getIcon("/nodes/servlet.png")),
    SortBySeverity(IconLoader.getIcon("/nodes/sortBySeverity.png")),
    SourceFolder(IconLoader.getIcon("/nodes/sourceFolder.png")),
    Static(IconLoader.getIcon("/nodes/static.png")),
    TestSourceFolder(IconLoader.getIcon("/nodes/testSourceFolder.png")),
    Undeploy(IconLoader.getIcon("/nodes/undeploy.png")),
    UnknownJdk(IconLoader.getIcon("/nodes/unknownJdk.png")),
    UpFolder(IconLoader.getIcon("/nodes/upFolder.png")),
    UpLevel(IconLoader.getIcon("/nodes/upLevel.png")),
    Variable(IconLoader.getIcon("/nodes/variable.png")),
    WarningIntroduction(IconLoader.getIcon("/nodes/warningIntroduction.png")),
    WebFolder(IconLoader.getIcon("/nodes/webFolder.png")),
    WebListener(IconLoader.getIcon("/nodes/weblistener.png")),
    WriteAccess(IconLoader.getIcon("/nodes/write-access.png"));

    private final Icon icon;

    FolderIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public boolean isCustom() {
        return false;
    }

    public static IIconGetter loadIcon(String url) throws IOException {
        VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(url);
        if (file == null) {
            // File no longer exists - return common custom folder icon.
            return Custom;
        }

        ImageIcon icon = new ImageIcon(file.contentsToByteArray());

        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        if (height > 16 || width > 16) {
            BufferedImage im = ImageUtil.createImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);

            if (height == width) {
                im.getGraphics().drawImage(icon.getImage(), 0, 0, 16, 16, null);
            } else if (height > width) {
                int newHeight = (int) ((height * 16.) / width);
                im.getGraphics().drawImage(icon.getImage(), 0, 0, 16, newHeight, null);
            } else {
                int newWidth = (int) ((width * 16.) / height);
                im.getGraphics().drawImage(icon.getImage(), 0, 0, newWidth, 16, null);
            }


            icon = new ImageIcon(im);
        }

        return new CustomIcon(url, icon);
    }

    public static IIconGetter getIcon(String iconName) {
        try {
            return FolderIcon.valueOf(iconName);
        } catch (IllegalArgumentException e) {
            // Fall through - check external icon
        }

        // Check if the name is uri
        try {
            return loadIcon(iconName);
        } catch (IOException e) {
            // Fall through
        }

        return Default;
    }

    private static final class CustomIcon implements IIconGetter {
        private final String url;
        private final Icon icon;

        private CustomIcon(String url, Icon icon) {
            this.url = url;
            this.icon = icon;
        }

        @Override
        public Icon getIcon() {
            return icon;
        }

        @Override
        public String getName() {
            return url;
        }

        @Override
        public boolean isCustom() {
            return true;
        }
    }
}
